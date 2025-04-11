package com.pokfulamroad.admintemplate.system.mapper;

import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface SysRoleRelationMapper extends BaseMapper<SysRoleRelation> {

    List<Long> selectAllRoleIdByUserId(Long userId);

    void  deleteByUserId(Long userId);


}




