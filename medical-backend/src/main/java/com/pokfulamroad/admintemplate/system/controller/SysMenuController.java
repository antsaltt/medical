package com.pokfulamroad.admintemplate.system.controller;


import cn.hutool.core.lang.tree.Tree;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import com.pokfulamroad.admintemplate.system.entity.SysMenuDto;
import com.pokfulamroad.admintemplate.system.entity.SysMenuRequest;
import com.pokfulamroad.admintemplate.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/menu")
public class SysMenuController {


    private final SysMenuService sysMenuService;


    @PostMapping("/saveMenu")
    public CommonResult<Void> saveMenu(@RequestBody SysMenuRequest request) {
        sysMenuService.saveMenus(request);
        return CommonResult.ok();
    }

    @GetMapping("/menuOne/{menuId}")
    public CommonResult<SysMenuDto> menuOne(@PathVariable Long menuId) {
        SysMenuRequest sysMenuRequest = new SysMenuRequest();
        sysMenuRequest.setMenuId(menuId);
        SysMenuDto sysMenuDto = sysMenuService.getMenuOne(sysMenuRequest);
        return CommonResult.data(sysMenuDto);
    }

    @PostMapping("/editMenu")
    public CommonResult<Void> editMenu(@RequestBody SysMenuRequest request) {
        sysMenuService.editMenu(request);
        return CommonResult.ok();
    }

    @GetMapping("/deleteMenu/{menuId}")
    public CommonResult<Void> deleteMenu(@PathVariable Long menuId) {
        SysMenuRequest sysMenuRequest = new SysMenuRequest();
        sysMenuRequest.setMenuId(menuId);
        sysMenuService.deleteMenu(sysMenuRequest);
        return CommonResult.ok();
    }

    @GetMapping("/menuTree")
    public CommonResult<List<Tree<String>>> getMenuTree() {
        List<Tree<String>> menuTree = sysMenuService.getMenuTree();
        return CommonResult.data(menuTree);
    }

    @GetMapping("/entryMenuTree")
    public CommonResult<List<Tree<String>>> getEntryMenuTree() {
        List<Tree<String>> menuTree = sysMenuService.getEntryMenuTree();
        return CommonResult.data(menuTree);
    }
}
