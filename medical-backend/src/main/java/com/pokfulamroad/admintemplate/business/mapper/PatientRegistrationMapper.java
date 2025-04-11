package com.pokfulamroad.admintemplate.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationDto;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientRegistration;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PatientRegistrationMapper extends BaseMapper<PatientRegistration> {

    Page<PatientRegistrationDto> selectPatientRegistrationPage(@Param("request") PatientRegistrationRequest request, @Param("page") Page<Object> page);

    List<PatientRegistrationDto> selectPatientRegistrationList(@Param("request") PatientRegistrationRequest request);

}




