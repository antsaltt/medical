package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


@Data
public class PatientRegistrationDto {

    private String id;


    private String userIdCard;

    private String applyDate;

    private String departmentName;

    private String doctorName;

    private String realName;

    private String idCardNo;


    /**
    * 患者ID
    */
    private Long patientId;


    /**
    * 医生ID
    */
    private Long doctorId;


    /**
    * 挂号时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registrationTime;


    /**
    * 状态 1:已挂号 2:已取消
    */
    private String status;


    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
