package com.pokfulamroad.admintemplate.system.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleDto {

    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限字符串
     */
    private String roleKey;

    private String defaultRole;

    /**
     * 创建时间
     */
    private Date createTime;
}
