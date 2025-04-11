package com.pokfulamroad.admintemplate.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeDto;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientResume;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PatientResumeMapper extends BaseMapper<PatientResume> {

    Page<PatientResumeDto> selectPatientRegistrationPage(@Param("request") PatientResumeRequest request, @Param("page") Page<Object> page);

    List<PatientResumeDto> selectPatientRegistrationList(@Param("request") PatientResumeRequest request);


}




