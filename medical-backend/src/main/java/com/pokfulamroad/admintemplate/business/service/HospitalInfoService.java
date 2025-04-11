package com.pokfulamroad.admintemplate.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoDto;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.HospitalInfo;

import java.util.List;


public interface HospitalInfoService extends IService<HospitalInfo> {

    Page<HospitalInfoDto> getHospitalInfoPage(HospitalInfoRequest request);

    void addHospitalInfo(HospitalInfoRequest request);

    void deleteHospitalInfo(Long id);

    void updateHospitalInfo(HospitalInfoRequest request);

    HospitalInfoDto getHospitalInfoOne(Long id);


    List<HospitalInfoDto> getHospitalInfoList(HospitalInfoRequest request);
}
