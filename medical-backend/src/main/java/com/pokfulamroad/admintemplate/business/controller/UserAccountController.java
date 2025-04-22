package com.pokfulamroad.admintemplate.business.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;
import com.pokfulamroad.admintemplate.business.entity.UserAccountRequest;
import com.pokfulamroad.admintemplate.business.service.UserAccountService;
import com.pokfulamroad.admintemplate.business.utils.UserHolder;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用户授权controller
 * 用于处理用户的登录、注册
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userAccount")
@CrossOrigin
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody UserAccountRequest request, HttpSession httpSession) {
        String s = userAccountService.userLogin(request, httpSession);
        return CommonResult.data(s);
    }

    @PostMapping("/register")
    public CommonResult<Void> register(@RequestBody UserAccountRequest request,HttpSession httpSession) {
        userAccountService.register(request, httpSession);
        return CommonResult.ok();
    }

    @GetMapping("/me")
    public CommonResult<UserAccountDto> me() {
        UserAccountDto user = UserHolder.getUser();
        return CommonResult.data(user);
    }

    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        userAccountService.deleteUserAccount(id);
        return CommonResult.ok();
    }

    @PostMapping("/update")
    public CommonResult<Void> updateUserAccount(@RequestBody UserAccountRequest request) {
        userAccountService.updateUserAccount(request);
        return CommonResult.ok();
    }

    @PostMapping("/page")
    public CommonResult<Page<UserAccountDto>> page(@RequestBody UserAccountRequest request) {
        Page<UserAccountDto> userAccountPage = userAccountService.getUserAccountPage(request);
        return CommonResult.data(userAccountPage);
    }


    @GetMapping("/one/{id}")
    public CommonResult<UserAccountDto> saveMenu(@PathVariable Long id) {
        UserAccountDto userAccountOne = userAccountService.getUserAccountOne(id);
        return CommonResult.data(userAccountOne);
    }

    @GetMapping("/logout")
    public CommonResult<Void> logout(HttpSession httpSession) {
        // 检查用户是否已登录
        if (httpSession.getAttribute("user") == null) {
            return CommonResult.error("请先登录");
        }
        // 清除当前用户的会话信息
        httpSession.invalidate();

        // 返回成功响应
        return CommonResult.ok();
    }
}
