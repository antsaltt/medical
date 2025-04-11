package com.pokfulamroad.admintemplate.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.DepartmentInfo;

import java.util.List;


public interface DepartmentInfoService extends IService<DepartmentInfo> {


    void addDepartmentInfo(DepartmentInfoRequest request);

    void deleteDepartmentInfo(Long id);

    void updateDepartmentInfo(DepartmentInfoRequest request);

    DepartmentInfoDto getDepartmentInfoOne(Long id);


    List<DepartmentInfoDto> getDepartmentInfoList(DepartmentInfoRequest request);

}
