package com.pokfulamroad.admintemplate.business.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="user_account")
@Data
public class UserAccount  {

    @TableId
    private Long id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 角色 1.doctor 2.patient
    */
    private String role;

    /**
    * 状态 1:正常 2:停用
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createTime;


}
