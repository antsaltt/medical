package com.pokfulamroad.admintemplate.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;
import com.pokfulamroad.admintemplate.business.entity.UserAccountRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.UserAccount;

import javax.servlet.http.HttpSession;


public interface UserAccountService extends IService<UserAccount> {

    Page<UserAccountDto> getUserAccountPage(UserAccountRequest request);

    void addUserAccount(UserAccountRequest request);

    void deleteUserAccount(Long id);

    void updateUserAccount(UserAccountRequest request);

    UserAccountDto getUserAccountOne(Long id);

    String userLogin(UserAccountRequest request, HttpSession httpSession);

    void register(UserAccountRequest request, HttpSession httpSession);

}
