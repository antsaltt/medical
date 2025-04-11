package com.pokfulamroad.admintemplate.business.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="patient_registration")
@Data
public class PatientRegistration  {

    @TableId
    private Long id;

    /**
    * 患者ID
    */
    private Long resumeId;

    /**
    * 医生ID
    */
    private Long doctorId;

    /**
    * 挂号时间
    */
    private Integer timeQuantum;

    private String applyDate;

    /**
    * 状态 1:已挂号 2:已取消
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createTime;


}
