package com.pokfulamroad.admintemplate.common.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pokfulamroad.admintemplate.common.entity.PortalUserRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PortalUser;
import com.pokfulamroad.admintemplate.common.service.PortalAuthService;
import com.pokfulamroad.admintemplate.common.service.PortalUserService;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PortalAuthServiceImpl implements PortalAuthService {


    private final PortalUserService portalUserService;


    @Override
    public void doRegister(PortalUserRequest request) {
        String username = request.getUsername();

        LambdaQueryWrapper<PortalUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PortalUser::getUsername,username);
        PortalUser one = portalUserService.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(one)) {
            throw new ServiceException("账号已存在");
        }

        PortalUser portalUser = BeanUtil.copyProperties(request, PortalUser.class);
        portalUser.setCreateTime(new Date());

        portalUserService.save(portalUser);

    }

    @Override
    public String doLogin(PortalUserRequest graduateRequest) {
        String username = graduateRequest.getUsername();
        String password = graduateRequest.getPassword();

        LambdaQueryWrapper<PortalUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PortalUser::getUsername,username);
        PortalUser one = portalUserService.getOne(queryWrapper);
        if (ObjectUtil.isEmpty(one) || !one.getPassword().equals(password)) {
            throw new ServiceException("账号或密码错误");
        }

        StpUtil.login(one.getId());
        StpUtil.getTokenSession().set("portalLoginUser", one);
        return StpUtil.getTokenInfo().tokenValue;

    }
}
