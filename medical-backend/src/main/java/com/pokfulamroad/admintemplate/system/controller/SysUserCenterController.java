package com.pokfulamroad.admintemplate.system.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.tree.Tree;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import com.pokfulamroad.admintemplate.system.entity.SysUserRequest;
import com.pokfulamroad.admintemplate.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/userCenter")
public class SysUserCenterController {


    private final SysUserService sysUserService;

    @GetMapping("/loginMenu")
    public CommonResult<List<Tree<String>>> loginMenu() {
        SysUserRequest sysUserRequest = new SysUserRequest();
        sysUserRequest.setUserId(Long.valueOf(StpUtil.getLoginIdAsString()));
        return CommonResult.data(sysUserService.ownMenu(sysUserRequest));
    }
}
