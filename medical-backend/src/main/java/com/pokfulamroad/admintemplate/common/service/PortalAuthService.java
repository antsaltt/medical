package com.pokfulamroad.admintemplate.common.service;


import com.pokfulamroad.admintemplate.common.entity.PortalUserRequest;

public interface PortalAuthService {


    String doLogin(PortalUserRequest graduateRequest);

    void doRegister(PortalUserRequest request);

}
