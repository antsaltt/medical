package com.pokfulamroad.admintemplate.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.system.auth.entity.SysLoginUser;
import com.pokfulamroad.admintemplate.system.entity.SysRoleRequest;
import com.pokfulamroad.admintemplate.system.entity.SysUserDto;
import com.pokfulamroad.admintemplate.system.entity.SysUserRequest;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRole;
import com.pokfulamroad.admintemplate.system.entity.domain.SysUser;

import java.util.List;


public interface SysUserService extends IService<SysUser> {

    SysLoginUser getUserByAccount(String account);

    List<SysUserDto> getUserList(SysUserRequest request);

    List<SysUserDto> getGrantedUsers(SysRoleRequest sysRoleRequest);
    List<SysUserDto> getUnGrantedUsers(SysRoleRequest sysRoleRequest);


    // 获取用户拥有的菜单
    List<Tree<String>> ownMenu(SysUserRequest request);

    /**
     * 获取用户拥有角色id
     */
    List<Long> ownRole(SysUserRequest request);
    /**
     * 获取角色详情集合
     */

    List<SysRole> getRoleListByIdList(SysUserRequest sysUserRequest);

    SysUserDto getUserOne(SysUserRequest sysUserRequest);

    void editUser(SysUserRequest request);

    void userDelete(Long userId);

}
