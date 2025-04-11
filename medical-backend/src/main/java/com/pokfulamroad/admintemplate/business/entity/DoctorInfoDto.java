package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


@Data
public class DoctorInfoDto {

    private String id;

    /**
    * 医生姓名
    */
    private String name;

    private String avatar;

    private String description;

    private List<String> appointmentTime;


    /**
    * 性别 1:男 2:女
    */
    private String gender;


    /**
    * 出生日期
    */
    private Date birthday;


    /**
    * 职称
    */
    private String title;


    /**
    * 所属科室ID
    */
    private String departmentId;

    private String departmentName;

    private String hosId;

    private String hosName;


    /**
    * 状态 1:正常 2:离职 3:休假
    */
    private String status;


    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Float rate;

}
