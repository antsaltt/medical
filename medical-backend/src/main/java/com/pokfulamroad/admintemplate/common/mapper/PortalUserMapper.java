package com.pokfulamroad.admintemplate.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.common.entity.PortalUserDto;
import com.pokfulamroad.admintemplate.common.entity.PortalUserRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PortalUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PortalUserMapper extends BaseMapper<PortalUser> {

    Page<PortalUserDto> selectUsersPage(@Param("request") PortalUserRequest request, @Param("page") Page<Object> page);

    List<PortalUserDto> selectUsersList(@Param("request") PortalUserRequest request);

}




