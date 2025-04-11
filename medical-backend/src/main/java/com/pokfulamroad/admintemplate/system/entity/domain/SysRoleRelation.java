package com.pokfulamroad.admintemplate.system.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName SYS_ROLE_RELATION
 *
 */
@TableName(value ="SYS_ROLE_RELATION")
@Data
public class SysRoleRelation implements Serializable {


    /**
     *
     */
    @TableId
    private Long id;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long roleId;

    /**
     *
     */
    private Date createdTime;

}
