package com.pokfulamroad.admintemplate.business.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeDto;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientResume;

import java.util.List;

public interface PatientResumeService extends IService<PatientResume> {

    Page<PatientResumeDto> getPatientResumePage(PatientResumeRequest request);

    void addPatientResume(PatientResumeRequest request);

    void deletePatientResume(Long id);

    boolean updatePatientResume(PatientResumeRequest request);

    PatientResumeDto getPatientResumeOne(Long request);

    List<PatientResumeDto> getPatientResumeList();

}
