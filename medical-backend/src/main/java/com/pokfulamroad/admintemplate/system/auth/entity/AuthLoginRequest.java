package com.pokfulamroad.admintemplate.system.auth.entity;


import lombok.Data;

@Data
public class AuthLoginRequest {


    private Long userId;

    private String account;

    private String username;


    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 1 男 0 女
     */
    private String gender;
}
