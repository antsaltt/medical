package com.pokfulamroad.admintemplate.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoDto;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientInfo;
import com.pokfulamroad.admintemplate.business.mapper.PatientInfoMapper;
import com.pokfulamroad.admintemplate.business.service.PatientInfoService;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PatientInfoServiceImpl extends ServiceImpl<PatientInfoMapper, PatientInfo>
        implements PatientInfoService {


    @Override
    public Page<PatientInfoDto> getPatientInfoPage(PatientInfoRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(request.getPageSize()).setCurrent(request.getCurrent());
        return this.baseMapper.selectPatientInfoPage(request, objectPage);
    }

    @Override
    public void addPatientInfo(PatientInfoRequest request) {
        PatientInfo patientInfo = BeanUtil.copyProperties(request, PatientInfo.class);
        patientInfo.setCreateTime(new Date());
        this.baseMapper.insert(patientInfo);
    }

    @Override
    public void deletePatientInfo(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updatePatientInfo(PatientInfoRequest request) {
        PatientInfo patientInfo = BeanUtil.copyProperties(request, PatientInfo.class);
        this.baseMapper.updateById(patientInfo);
    }

    @Override
    public PatientInfoDto getPatientInfoOne(Long id) {
        LambdaQueryWrapper<PatientInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PatientInfo::getId, id);
        PatientInfo one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, PatientInfoDto.class);
    }

}




