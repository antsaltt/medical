package com.pokfulamroad.admintemplate.system.auth.service;


import com.pokfulamroad.admintemplate.system.auth.entity.AuthLoginRequest;
import com.pokfulamroad.admintemplate.system.auth.entity.SysLoginUser;
import com.pokfulamroad.admintemplate.system.entity.domain.SysUser;

public interface AuthService  {


    String doLogin(AuthLoginRequest authLoginRequest);

    SysLoginUser getLoginUser();

    void createAccount(AuthLoginRequest request);
    SysUser createAccountWithRole(AuthLoginRequest request, String roleKey);


    void resetPassword(AuthLoginRequest request);

    void doLogout();

    void deleteAccount(AuthLoginRequest request);
}
