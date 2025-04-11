package com.pokfulamroad.admintemplate.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PortalUserDto {

    private String id;

    /**
     * 用户名
     */
    private String username;


    /**
     * 手机号
     */
    private String phoneNum;


    /**
     * 密码
     */
    private String password;


    /**
     * 创建时间
     */
    private Date createTime;;
}
