package com.pokfulamroad.admintemplate.common.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.pokfulamroad.admintemplate.common.entity.PortalUserDto;
import com.pokfulamroad.admintemplate.common.entity.PortalUserRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PortalUser;
import com.pokfulamroad.admintemplate.common.service.PortalAuthService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portal/auth")
public class PortalAuthController {

    
    private final PortalAuthService portalAuthService;


    @GetMapping("/loginUser")
    public CommonResult<PortalUserDto> delete() {
        PortalUser portalLoginUser = (PortalUser)StpUtil.getTokenSession().get("portalLoginUser");
        PortalUserDto portalUserDto = BeanUtil.copyProperties(portalLoginUser, PortalUserDto.class);
        return CommonResult.data((portalUserDto));
    }

    @GetMapping("/isLogin")
    public CommonResult<Object> isLogin() {
        boolean login = StpUtil.isLogin();
        return CommonResult.data(login);
    }

    @PostMapping("/doLogin")
    public CommonResult<String> doLogin(@RequestBody PortalUserRequest request) {
        String s = portalAuthService.doLogin(request);
        return CommonResult.data(s);
    }
    @PostMapping("/doRegister")
    public CommonResult<Void> doRegister(@RequestBody PortalUserRequest request) {
        portalAuthService.doRegister(request);
        return CommonResult.ok();
    }
}
