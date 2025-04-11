package com.pokfulamroad.admintemplate.business.entity;

import com.pokfulamroad.admintemplate.system.entity.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientRegistrationRequest extends BaseRequest {

    private Long id;

    /**
     * 患者ID
     */
    private Long patientId;

    private Long resumeId;

    /**
     * 医生ID
     */
    private Long doctorId;


    /**
     * 挂号时间段
     */
    private String timeQuantum;


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
