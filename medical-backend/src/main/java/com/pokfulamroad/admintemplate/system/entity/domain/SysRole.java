package com.pokfulamroad.admintemplate.system.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表
 * @TableName SYS_ROLE
 */
@TableName(value ="SYS_ROLE")
@Data
public class SysRole implements Serializable {

    /**
     * 角色ID
     */
    @TableId
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    private String defaultRole;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 创建时间
     */
    private Date createTime;

}
