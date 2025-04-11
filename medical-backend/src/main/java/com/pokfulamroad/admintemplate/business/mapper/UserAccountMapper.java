package com.pokfulamroad.admintemplate.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;
import com.pokfulamroad.admintemplate.business.entity.UserAccountRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserAccountMapper extends BaseMapper<UserAccount> {

    Page<UserAccountDto> selectUserAccountPage(@Param("request") UserAccountRequest request, @Param("page") Page<Object> page);

    List<UserAccountDto> selectUserAccountList(@Param("request") UserAccountRequest request);

}




