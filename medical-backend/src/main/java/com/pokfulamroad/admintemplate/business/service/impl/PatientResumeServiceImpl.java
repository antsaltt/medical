package com.pokfulamroad.admintemplate.business.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeDto;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeRequest;
import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientResume;
import com.pokfulamroad.admintemplate.business.mapper.PatientResumeMapper;
import com.pokfulamroad.admintemplate.business.service.PatientResumeService;
import com.pokfulamroad.admintemplate.business.utils.UserHolder;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientResumeServiceImpl extends ServiceImpl<PatientResumeMapper, PatientResume>
        implements PatientResumeService {


    @Override
    public Page<PatientResumeDto> getPatientResumePage(PatientResumeRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(request.getPageSize()).setCurrent(request.getCurrent());
        return this.baseMapper.selectPatientRegistrationPage(request, objectPage);
    }

    @Override
    public void addPatientResume(PatientResumeRequest request) {
        UserAccountDto user = UserHolder.getUser();
        PatientResume patientResume = BeanUtil.copyProperties(request, PatientResume.class);
        patientResume.setUserId(Long.valueOf(user.getId()));

        this.baseMapper.insert(patientResume);
    }

    @Override
    public void deletePatientResume(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public boolean updatePatientResume(PatientResumeRequest request) {
        PatientResume PatientResume = BeanUtil.copyProperties(request, PatientResume.class);
        int updated=this.baseMapper.updateById(PatientResume);
        return updated > 0;
    }

    @Override
    public PatientResumeDto getPatientResumeOne(Long id) {
        LambdaQueryWrapper<PatientResume> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PatientResume::getId, id);
        PatientResume one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, PatientResumeDto.class);
    }

    @Override
    public List<PatientResumeDto> getPatientResumeList() {

        UserAccountDto user = UserHolder.getUser();
        PatientResumeRequest patientResumeRequest = new PatientResumeRequest();
        patientResumeRequest.setUserId(user.getId());
        return this.baseMapper.selectPatientRegistrationList(patientResumeRequest);
    }

}




