package com.pokfulamroad.admintemplate.system.auth.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pokfulamroad.admintemplate.system.auth.entity.AuthLoginRequest;
import com.pokfulamroad.admintemplate.system.auth.entity.SysLoginUser;
import com.pokfulamroad.admintemplate.system.auth.service.AuthService;
import com.pokfulamroad.admintemplate.system.auth.utils.StpLoginUserUtil;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRole;
import com.pokfulamroad.admintemplate.system.entity.domain.SysUser;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import com.pokfulamroad.admintemplate.system.service.SysRoleRelationService;
import com.pokfulamroad.admintemplate.system.service.SysRoleService;
import com.pokfulamroad.admintemplate.system.service.SysUserService;
import com.pokfulamroad.admintemplate.system.util.CommonAvatarUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


   private final SysUserService sysUserService;

   private final SysRoleService sysRoleService;


   private final SysRoleRelationService sysRoleRelationService;




    private String execLoginB(SysLoginUser sysLoginUser) {

        // 是否允许登录

        // 执行登录
        StpUtil.login(sysLoginUser.getUserId());

        // 角色集合
        List<SysRole> roleList = sysRoleService.getRoleList(sysLoginUser.getUserId());
        // 角色id集合
        // 角色码集合
        List<String> roleKeyList = roleList.stream().map(SysRole::getRoleKey).collect(Collectors.toList());

        // 设置角色码
        sysLoginUser.setRoleKeyList(roleKeyList);

        StpUtil.getTokenSession().set("loginUser", sysLoginUser);

        return StpUtil.getTokenInfo().tokenValue;
    }

    @Override
    public String doLogin(AuthLoginRequest authLoginRequest) {
        // 判断是否被封禁
        String account = authLoginRequest.getAccount();
        String password = authLoginRequest.getPassword();

        SysLoginUser loginUser = sysUserService.getUserByAccount(account);
        if (ObjectUtil.isEmpty(loginUser) || !loginUser.getPassword().equals(password)) {
            throw new ServiceException("账号或密码错误");
        }
        return execLoginB(loginUser);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAccount(AuthLoginRequest request) {
        Long userId = request.getUserId();
        // 删除用户
        sysUserService.userDelete(userId);
        // 删除用户与角色的关联
        sysRoleRelationService.removeRoleRelationByUserId(userId);
    }

    @Override
    public void doLogout() {
        StpUtil.logout();
    }

    @Override
    public void resetPassword(AuthLoginRequest request) {
        SysUser sysUser = sysUserService.getById(request.getUserId());
        sysUser.setPassword("000000");
        sysUserService.updateById(sysUser);
    }

    @Override
    public SysUser createAccountWithRole(AuthLoginRequest request, String roleKey) {
        SysUser sysUser = BeanUtil.copyProperties(request, SysUser.class);
        String avatarString = CommonAvatarUtil.generateImg(sysUser.getUsername());
        sysUser.setPassword(request.getPassword());
        sysUser.setAvatar(avatarString);

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,request.getAccount());
        SysUser s = sysUserService.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(s)) {
            throw new ServiceException("账号名已存在");
        }

        sysUserService.save(sysUser);
        // 为用户分配默认角色
        sysRoleService.grantRoleToUser(sysUser.getUserId(),roleKey);
        return  sysUser;
    }

    @Override
    public void createAccount(AuthLoginRequest request) {

        SysUser sysUser = BeanUtil.copyProperties(request, SysUser.class);
        String avatarString = CommonAvatarUtil.generateImg(sysUser.getUsername());
        sysUser.setPassword("000000");
        sysUser.setAvatar(avatarString);

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,request.getAccount());
        SysUser s = sysUserService.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(s)) {
            throw new ServiceException("账号名已存在");
        }

        sysUserService.save(sysUser);
        // 为用户分配默认角色
      //  sysRoleService.grantDefaultRoleToUser(sysUser.getUserId());
    }

    @Override
    public SysLoginUser getLoginUser() {
        return StpLoginUserUtil.getLoginUser();
    }
}
