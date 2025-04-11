package com.pokfulamroad.admintemplate.business.entity;


import com.pokfulamroad.admintemplate.system.entity.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientResumeRequest extends BaseRequest {

    private Long id;

    private String idCardNo;

    private String userId;

    private String realName;

    private String gender;

    private String defaultResume;
}
