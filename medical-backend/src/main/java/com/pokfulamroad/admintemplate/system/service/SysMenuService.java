package com.pokfulamroad.admintemplate.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.system.entity.SysMenuDto;
import com.pokfulamroad.admintemplate.system.entity.SysMenuRequest;
import com.pokfulamroad.admintemplate.system.entity.domain.SysMenu;

import java.util.List;


public interface SysMenuService extends IService<SysMenu> {


    List<Tree<String>> getMenuTree();

    List<Tree<String>> getEntryMenuTree();

    void saveMenus(SysMenuRequest request);

    void deleteMenu(SysMenuRequest request);

    SysMenuDto getMenuOne(SysMenuRequest request);

    void editMenu(SysMenuRequest request);
}
