package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


@Data
public class PatientInfoDto {

    private String id;

    /**
    * 患者姓名
    */
    private String name;

    private Integer rate;

    /**
    * 性别 1:男 2:女
    */
    private String gender;


    /**
    * 年龄
    */
    private Integer age;


    /**
    * 联系电话
    */
    private String phone;


    /**
    * 联系地址
    */
    private String address;


    /**
    * 状态 1:正常 2:停诊
    */
    private String status;


    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
