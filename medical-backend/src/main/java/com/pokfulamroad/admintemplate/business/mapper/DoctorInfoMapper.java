package com.pokfulamroad.admintemplate.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.DoctorInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DoctorInfoMapper extends BaseMapper<DoctorInfo> {

    Page<DoctorInfoDto> selectDoctorInfoPage(@Param("request") DoctorInfoRequest request, @Param("page") Page<Object> page);

    List<DoctorInfoDto> selectDoctorInfoList(@Param("request") DoctorInfoRequest request);


    DoctorInfoDto selectDoctotOne(Long id);

}




