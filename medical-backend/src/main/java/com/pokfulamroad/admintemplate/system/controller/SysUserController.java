package com.pokfulamroad.admintemplate.system.controller;


import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import com.pokfulamroad.admintemplate.system.entity.SysUserDto;
import com.pokfulamroad.admintemplate.system.entity.SysUserRequest;
import com.pokfulamroad.admintemplate.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/user")
public class SysUserController {


    private final SysUserService sysUserService;

    @PostMapping("/userList")
    public CommonResult<List<SysUserDto>> userList(@RequestBody SysUserRequest request) {
        return CommonResult.data(sysUserService.getUserList(request));
    }

    @GetMapping("/userOne/{userId}")
    public CommonResult<SysUserDto> userOne(@PathVariable Long userId) {
        SysUserRequest sysUserRequest = new SysUserRequest();
        sysUserRequest.setUserId(userId);
        return CommonResult.data(sysUserService.getUserOne(sysUserRequest));
    }



    @PostMapping("/userEdit")
    public CommonResult<Void> userEdit(@RequestBody SysUserRequest request) {
        sysUserService.editUser(request);
        return CommonResult.ok();
    }
}
