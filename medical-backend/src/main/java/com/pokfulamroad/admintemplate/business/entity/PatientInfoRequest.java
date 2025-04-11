package com.pokfulamroad.admintemplate.business.entity;

import com.pokfulamroad.admintemplate.system.entity.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientInfoRequest extends BaseRequest {

    private Long id;

    /**
    * 患者姓名
    */
    private String name;

    private Integer rate;

    private Long departmentId;



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
