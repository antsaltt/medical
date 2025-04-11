package com.pokfulamroad.admintemplate.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.system.entity.*;
import com.pokfulamroad.admintemplate.system.service.SysRoleService;
import com.pokfulamroad.admintemplate.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/role")
public class SysRoleController {


    private final SysRoleService sysRoleService;

    private final SysUserService sysUserService;

    @PostMapping("/grantResource")
    public CommonResult<Void> grantResource(@RequestBody SysRoleRequest sysRoleRequest) {
        sysRoleService.grantResource(sysRoleRequest);
        return CommonResult.ok();
    }

    @GetMapping("/getGrantedUsers/{roleId}")
    public CommonResult<List<SysUserDto>> getGrantedUsers(@PathVariable Long roleId) {
        SysRoleRequest sysRoleRequest = new SysRoleRequest();
        sysRoleRequest.setRoleId(roleId);
        List<SysUserDto> grantedUsers = sysUserService.getGrantedUsers(sysRoleRequest);
        return CommonResult.data(grantedUsers);
    }

    @GetMapping("/getUnGrantedUsers/{roleId}")
    public CommonResult<List<SysUserDto>> getUnGrantedUsers(@PathVariable Long roleId) {
        SysRoleRequest sysRoleRequest = new SysRoleRequest();
        sysRoleRequest.setRoleId(roleId);
        List<SysUserDto> grantedUsers = sysUserService.getUnGrantedUsers(sysRoleRequest);
        return CommonResult.data(grantedUsers);
    }

    @GetMapping("/hasGrantedResource/{roleId}")
    public CommonResult<List<SysMenuDto>> hasGrantedResource(@PathVariable Long roleId) {
        SysRoleRequest sysRoleRequest = new SysRoleRequest();
        sysRoleRequest.setRoleId(roleId);
        List<SysMenuDto> sysMenuDtos = sysRoleService.hasGrantedResource(sysRoleRequest);
        return CommonResult.data(sysMenuDtos);
    }

    @PostMapping("/grantToUser")
    public CommonResult<Void> grantToUser(@RequestBody SysRoleRequest sysRoleRequest) {
        sysRoleService.grantToUser(sysRoleRequest);
        return CommonResult.ok();
    }

    @PostMapping("/rolePage")
    public CommonResult<Page<SysRoleDto>> rolePage(@RequestBody SysRoleRequest sysRoleRequest) {
        Page<SysRoleDto> rolePage = sysRoleService.getRolePage(sysRoleRequest);
        return CommonResult.data(rolePage);
    }

    @GetMapping("/roleOne/{roleId}")
    public CommonResult<SysRoleDto> roleOne(@PathVariable Long roleId) {
        SysRoleRequest sysRoleRequest = new SysRoleRequest();
        sysRoleRequest.setRoleId(roleId);
        SysRoleDto roleOne = sysRoleService.getRoleOne(sysRoleRequest);
        return CommonResult.data(roleOne);
    }

    @GetMapping("/deleteRole/{roleId}")
    public CommonResult<Void> deleteRole(@PathVariable Long roleId) {
        SysRoleRequest sysRoleRequest = new SysRoleRequest();
        sysRoleRequest.setRoleId(roleId);
        sysRoleService.deleteRole(sysRoleRequest);
        return CommonResult.ok();
    }

    @PostMapping("/saveRole")
    public CommonResult<Void> saveRole(@RequestBody SysRoleRequest sysRoleRequest) {
        sysRoleService.saveRole(sysRoleRequest);
        return CommonResult.ok();
    }

    @PostMapping("/editRole")
    public CommonResult<Void> editRole(@RequestBody SysRoleRequest sysRoleRequest) {
        sysRoleService.editRole(sysRoleRequest);
        return CommonResult.ok();
    }
}
