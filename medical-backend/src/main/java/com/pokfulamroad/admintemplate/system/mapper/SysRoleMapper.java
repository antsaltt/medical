package com.pokfulamroad.admintemplate.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.system.entity.SysRoleDto;
import com.pokfulamroad.admintemplate.system.entity.SysRoleRequest;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRole;
import org.apache.ibatis.annotations.Param;


public interface SysRoleMapper extends BaseMapper<SysRole> {


    Page<SysRoleDto> selectSysrolePage(@Param("page") Page page, @Param("request") SysRoleRequest request);

}




