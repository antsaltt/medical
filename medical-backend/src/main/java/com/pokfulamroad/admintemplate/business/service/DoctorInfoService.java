package com.pokfulamroad.admintemplate.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.UserRateRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.DoctorInfo;

import java.util.Set;


public interface DoctorInfoService extends IService<DoctorInfo> {

    Page<DoctorInfoDto> getDoctorInfoPage(DoctorInfoRequest request);

    void addDoctorInfo(DoctorInfoRequest request);

    void deleteDoctorInfo(Long id);

    void updateDoctorInfo(DoctorInfoRequest request);

    DoctorInfoDto getDoctorInfoOne(Long id);

    void saveUserRate(UserRateRequest userRateRequest);

    Set<String> getUserRateList();

}
