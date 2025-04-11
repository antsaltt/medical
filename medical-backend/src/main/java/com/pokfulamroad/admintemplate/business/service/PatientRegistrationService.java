package com.pokfulamroad.admintemplate.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationDto;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientRegistration;

import java.util.List;


public interface PatientRegistrationService extends IService<PatientRegistration> {

    Page<PatientRegistrationDto> getPatientRegistrationPage(PatientRegistrationRequest request);

    void addPatientRegistration(PatientRegistrationRequest request);

    void deletePatientRegistration(Long id);


    PatientRegistrationDto getPatientRegistrationOne(Long id);


    List<PatientRegistrationDto> getPatientRegList();


}
