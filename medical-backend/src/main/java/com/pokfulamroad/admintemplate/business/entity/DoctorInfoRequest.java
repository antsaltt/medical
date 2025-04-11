package com.pokfulamroad.admintemplate.business.entity;

import com.pokfulamroad.admintemplate.system.entity.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DoctorInfoRequest extends BaseRequest {

    private Long id;

    /**
     * 医生姓名
     */
    private String name;


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
    private Long departmentId;

    private Long hosId;


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
