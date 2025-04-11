package com.pokfulamroad.admintemplate.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoDto;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PatientInfoMapper extends BaseMapper<PatientInfo> {

    Page<PatientInfoDto> selectPatientInfoPage(@Param("request") PatientInfoRequest request, @Param("page") Page<Object> page);

    List<PatientInfoDto> selectPatientInfoList(@Param("request") PatientInfoRequest request);

}




