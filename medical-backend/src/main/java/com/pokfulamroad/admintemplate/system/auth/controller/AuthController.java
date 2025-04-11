package com.pokfulamroad.admintemplate.system.auth.controller;


import com.pokfulamroad.admintemplate.system.auth.entity.AuthLoginRequest;
import com.pokfulamroad.admintemplate.system.auth.entity.SysLoginUser;
import com.pokfulamroad.admintemplate.system.auth.service.AuthService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {



    private final AuthService authService;


    @PostMapping("/doLogin")
    public CommonResult<String> page(@RequestBody AuthLoginRequest request) {
        return CommonResult.data(authService.doLogin(request));
    }

    @GetMapping("/doLogout")
    public CommonResult<Void> doLogout() {
        authService.doLogout();
        return CommonResult.ok();
    }

    @GetMapping("/getLoginUser")
    public CommonResult<SysLoginUser> getLoginUser() {
        return CommonResult.data(authService.getLoginUser());
    }

    @PostMapping("/createAccount")
    public CommonResult<Void> createAccount(@RequestBody AuthLoginRequest request) {
        authService.createAccount(request);
        return CommonResult.ok();
    }
    @PostMapping("/deleteAccount")
    public CommonResult<Void> deleteAccount(@RequestBody AuthLoginRequest request) {
        authService.deleteAccount(request);
        return CommonResult.ok();
    }

    @PostMapping("/resetPassword")
    public CommonResult<Void> resetPassword(@RequestBody AuthLoginRequest request) {
        authService.resetPassword(request);
        return CommonResult.ok();
    }
}
