package com.pokfulamroad.admintemplate.system.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class SysUserDto {

    private String userId;

    private String avatar;

    /**
     * 用户名
     */
    private String username;

    private String account;

    private List<String> roleName;
    /**
     * 密码
     */
    @JsonIgnore
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
