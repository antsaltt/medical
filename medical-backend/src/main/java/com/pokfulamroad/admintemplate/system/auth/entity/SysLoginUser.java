package com.pokfulamroad.admintemplate.system.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysLoginUser {

    private Long userId;

    @JsonIgnore
    private String password;

    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 1 男 0 女
     */
    private String gender;

    /**
     * 创建时间
     */

    private Date createTime;

    private List<String> roleKeyList;
}
