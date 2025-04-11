package com.pokfulamroad.admintemplate.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoDto;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientInfo;


public interface PatientInfoService extends IService<PatientInfo> {

    Page<PatientInfoDto> getPatientInfoPage(PatientInfoRequest request);

    void addPatientInfo(PatientInfoRequest request);

    void deletePatientInfo(Long id);

    void updatePatientInfo(PatientInfoRequest request);

    PatientInfoDto getPatientInfoOne(Long id);


}
