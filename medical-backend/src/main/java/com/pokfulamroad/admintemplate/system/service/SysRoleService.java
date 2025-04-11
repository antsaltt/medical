package com.pokfulamroad.admintemplate.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.system.entity.SysMenuDto;
import com.pokfulamroad.admintemplate.system.entity.SysRoleDto;
import com.pokfulamroad.admintemplate.system.entity.SysRoleRequest;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRole;

import java.util.List;


public interface SysRoleService extends IService<SysRole> {




    void grantResource(SysRoleRequest sysRoleRequest);

    Page<SysRoleDto> getRolePage(SysRoleRequest sysRoleRequest);

    void editRole(SysRoleRequest sysRoleRequest);
    void saveRole(SysRoleRequest sysRoleRequest);

    SysRoleDto getRoleOne(SysRoleRequest sysRoleRequest);

    void deleteRole(SysRoleRequest sysRoleRequest);


    void grantToUser(SysRoleRequest sysRoleRequest);


    List<SysMenuDto> hasGrantedResource(SysRoleRequest sysRoleRequest);


    /**
     *  根据用户ID 获取 roleKey

     */
    String getRoleKeyByUserId(Long userId);


    List<SysRole> getRoleList(Long userId);



    void grantRoleToUser(Long userId,Long roleId);
    void grantRoleToUser(Long userId,String roleKey);
    void grantDefaultRoleToUser(Long userId);

}
