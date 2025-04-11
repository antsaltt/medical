package com.pokfulamroad.admintemplate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleRelation;

import java.util.List;


public interface SysRoleRelationService extends IService<SysRoleRelation> {

    /**
     * 根据userId 获取所有RoleId
     */
    List<Long> getAllUserRoleId(Long userId);

    void removeRoleRelationByUserId(Long userId);


    List<Long> getGrantedUserIds(Long roleId);

}
