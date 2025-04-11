package com.pokfulamroad.admintemplate.system.mapper;

import com.pokfulamroad.admintemplate.system.entity.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface SysUserMapper extends BaseMapper<SysUser> {


    SysUser selectByAccount(@Param("account") String account);

}




