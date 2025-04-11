package com.pokfulamroad.admintemplate.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.common.entity.PortalUserDto;
import com.pokfulamroad.admintemplate.common.entity.PortalUserRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PortalUser;


public interface PortalUserService extends IService<PortalUser> {

    Page<PortalUserDto> getUsersPage(PortalUserRequest request);

    void addUsers(PortalUserRequest request);

    void deleteUsers(Long id);

    void updateUsers(PortalUserRequest request);

    PortalUserDto getUsersOne(Long id);



}
