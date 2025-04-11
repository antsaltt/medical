package com.pokfulamroad.admintemplate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleMenu;

import java.util.List;


public interface SysRoleMenuService extends IService<SysRoleMenu> {


    void saveMenusToRole(List<SysRoleMenu> list);

    List<Long> getMenuIdsByRoleIds(List<Long> roleIds);


}
