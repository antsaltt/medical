package com.pokfulamroad.admintemplate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import com.pokfulamroad.admintemplate.system.entity.SysMenuDto;
import com.pokfulamroad.admintemplate.system.entity.SysRoleDto;
import com.pokfulamroad.admintemplate.system.entity.SysRoleRequest;
import com.pokfulamroad.admintemplate.system.entity.domain.SysMenu;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRole;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleMenu;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleRelation;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import com.pokfulamroad.admintemplate.system.mapper.SysRoleMapper;
import com.pokfulamroad.admintemplate.system.service.SysMenuService;
import com.pokfulamroad.admintemplate.system.service.SysRoleMenuService;
import com.pokfulamroad.admintemplate.system.service.SysRoleRelationService;
import com.pokfulamroad.admintemplate.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;

    private final SysMenuService sysMenuService;

    private final SysRoleRelationService sysRoleRelationService;


    @Override
    public void grantDefaultRoleToUser(Long userId) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRole::getRoleKey, "admin");

        SysRole serviceOne = this.getOne(queryWrapper);
        SysRoleRelation roleRelation = new SysRoleRelation();
        roleRelation.setUserId(userId);
        roleRelation.setRoleId(serviceOne.getRoleId());

        sysRoleRelationService.save(roleRelation);
    }

    @Override
    public void grantRoleToUser(Long userId, String roleKey) {
        SysRole one = this.getOne(Wrappers.lambdaQuery(SysRole.class)
                .eq(SysRole::getRoleKey, roleKey));
        if (ObjectUtil.isEmpty(one)) {
            throw new ServiceException("角色不存在");
        }


        LambdaQueryWrapper<SysRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleRelation::getUserId, userId);
        queryWrapper.eq(SysRoleRelation::getRoleId, one.getRoleId());
        SysRoleRelation one1 = sysRoleRelationService.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(one1)) {
            throw new ServiceException("用户已有该角色");
        }

        SysRoleRelation roleRelation = new SysRoleRelation();
        roleRelation.setUserId(userId);
        roleRelation.setRoleId(one.getRoleId());

        sysRoleRelationService.save(roleRelation);
    }

    @Override
    public void grantRoleToUser(Long userId, Long roleId) {
        LambdaQueryWrapper<SysRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleRelation::getUserId, userId);
        queryWrapper.eq(SysRoleRelation::getRoleId, roleId);
        SysRoleRelation one = sysRoleRelationService.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(one)) {
            throw new ServiceException("用户已有该角色");
        }

        SysRoleRelation roleRelation = new SysRoleRelation();
        roleRelation.setUserId(userId);
        roleRelation.setRoleId(roleId);

        sysRoleRelationService.save(roleRelation);
    }



    @Override
    public List<SysRole> getRoleList(Long userId) {
        List<Long> allUserRoleId = sysRoleRelationService.getAllUserRoleId(userId);
        if (CollUtil.isNotEmpty(allUserRoleId)) {
            return this.listByIds(allUserRoleId).stream().toList();
        }
        return Collections.emptyList();
    }


    @Override
    public Page<SysRoleDto> getRolePage(SysRoleRequest request) {
        return this.baseMapper.selectSysrolePage(CommonPageRequest.defaultPage(), request);
    }

    @Override
    public void saveRole(SysRoleRequest sysRoleRequest) {
        SysRole sysRole = BeanUtil.copyProperties(sysRoleRequest, SysRole.class);
        this.save(sysRole);
    }

    @Override
    public void editRole(SysRoleRequest sysRoleRequest) {
        SysRole sysRole = BeanUtil.copyProperties(sysRoleRequest, SysRole.class);
        this.updateById(sysRole);
    }

    @Override
    public SysRoleDto getRoleOne(SysRoleRequest sysRoleRequest) {
        SysRole sysRole = this.getById(sysRoleRequest.getRoleId());
        return BeanUtil.copyProperties(sysRole, SysRoleDto.class);
    }

    @Override
    public void deleteRole(SysRoleRequest request) {

        SysRole sysRole = this.getById(request.getRoleId());
        if ("1".equals(sysRole.getDefaultRole())) {
            throw new ServiceException("默认角色不可删除");
        }
        this.removeById(request.getRoleId());
    }


    @Override
    public String getRoleKeyByUserId(Long userId) {
        SysRoleRelation one = sysRoleRelationService.getOne(Wrappers.lambdaQuery(SysRoleRelation.class).eq(SysRoleRelation::getUserId, userId));
        SysRole sysRole = this.getById(one.getRoleId());

        return sysRole.getRoleKey();
    }

    @Override
    public List<SysMenuDto> hasGrantedResource(SysRoleRequest sysRoleRequest) {
        List<Long> menuIds = sysRoleMenuService.getMenuIdsByRoleIds(List.of(sysRoleRequest.getRoleId()));
        List<SysMenu> sysMenus = sysMenuService.listByIds(menuIds);
        return BeanUtil.copyToList(sysMenus, SysMenuDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantToUser(SysRoleRequest sysRoleRequest) {
        Set<Long> userIds = sysRoleRequest.getUserIds();
        Long roleId = sysRoleRequest.getRoleId();
        LambdaQueryWrapper<SysRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleRelation::getRoleId, roleId);
        List<SysRoleRelation> relationList = sysRoleRelationService.getBaseMapper().selectList(queryWrapper);
        List<Long> roleUserId = relationList.stream().map(SysRoleRelation::getUserId).toList();

        List<Long> unRoleUserIds = userIds.stream().filter(x -> !roleUserId.contains(x)).toList();
        List<SysRoleRelation> unRoleRel = unRoleUserIds.stream().map(x -> {
            SysRoleRelation roleRelation = new SysRoleRelation();
            roleRelation.setRoleId(roleId);
            roleRelation.setUserId(x);
            return roleRelation;
        }).toList();

        sysRoleRelationService.saveBatch(unRoleRel);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantResource(SysRoleRequest sysRoleRequest) {
        Long roleId = sysRoleRequest.getRoleId();
        Set<Long> menuIds = sysRoleRequest.getMenuIds();

        List<Long> sysMenuId = sysMenuService.list().stream().filter(x -> x.getMenuType().equals("2")).map(SysMenu::getMenuId).toList();
        menuIds = menuIds.stream().filter(sysMenuId::contains).collect(Collectors.toSet());


        SysRole sysRole = this.baseMapper.selectById(roleId);

        // todo 不可将系统菜单授予非admin 角色

        List<SysRoleMenu> sysRoleMenuList = menuIds.stream().map(x -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setCreateTime(new Date());
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(x);
            return sysRoleMenu;
        }).toList();

        sysRoleMenuService.saveMenusToRole(sysRoleMenuList);


    }
}




