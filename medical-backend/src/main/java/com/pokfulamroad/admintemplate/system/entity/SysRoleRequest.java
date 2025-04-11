package com.pokfulamroad.admintemplate.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleRequest extends BaseRequest {


    private Long roleId;

    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;


    private Integer orderNum;

    private Set<Long> menuIds;

    private Set<Long> userIds;

}
