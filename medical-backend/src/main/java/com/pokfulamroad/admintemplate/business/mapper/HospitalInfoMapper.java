package com.pokfulamroad.admintemplate.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoDto;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.HospitalInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface HospitalInfoMapper extends BaseMapper<HospitalInfo> {

    Page<HospitalInfoDto> selectHospitalInfoPage(@Param("request") HospitalInfoRequest request, @Param("page") Page<Object> page);

    List<HospitalInfoDto> selectHospitalInfoList(@Param("request") HospitalInfoRequest request);

}




