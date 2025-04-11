package com.pokfulamroad.admintemplate.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.DepartmentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DepartmentInfoMapper extends BaseMapper<DepartmentInfo> {

    Page<DepartmentInfoDto> selectDepartmentInfoPage(@Param("request") DepartmentInfoRequest request, @Param("page") Page<Object> page);

    List<DepartmentInfoDto> selectDepartmentInfoList(@Param("request") DepartmentInfoRequest request);

}




