package com.pokfulamroad.admintemplate.common.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="portal_user")
@Data
public class PortalUser {



        @TableId
        private Long id;

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
