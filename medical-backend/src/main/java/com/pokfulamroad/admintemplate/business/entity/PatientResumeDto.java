package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;

@Data
public class PatientResumeDto {

    private String id;

    private String idCardNo;

    private String realName;

    private String gender;

    private String defaultResume;
}
