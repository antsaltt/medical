package com.pokfulamroad.admintemplate.system.entity;

import lombok.Data;

import java.util.List;


@Data
public class SysUserRequest {

    private Long userId;

    private List<Long> roleIdList;

    private String avatar;
    /**
     * 用户名
     */
    private String username;

    private String account;

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
