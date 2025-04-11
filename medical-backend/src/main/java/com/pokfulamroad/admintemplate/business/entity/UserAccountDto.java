package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


@Data
public class UserAccountDto {

    private String id;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
