package com.pokfulamroad.admintemplate.business.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeDto;
import com.pokfulamroad.admintemplate.business.entity.PatientResumeRequest;
import com.pokfulamroad.admintemplate.business.service.PatientResumeService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patientResume")
@CrossOrigin
public class PatientResumeController {

    private final PatientResumeService patientResumeService;

    @RequestMapping("/page")
    public CommonResult<Page<PatientResumeDto>> page(@RequestBody PatientResumeRequest request) {
        Page<PatientResumeDto> page = patientResumeService.getPatientResumePage(request);
        return CommonResult.data(page);
    }

    @GetMapping("/list")
    public CommonResult<List<PatientResumeDto>> list() {
        List<PatientResumeDto> patientResumeList = patientResumeService.getPatientResumeList();
        return CommonResult.data(patientResumeList);
    }

    @RequestMapping("/save")
    public CommonResult<Void> save(@RequestBody PatientResumeRequest request) {
        patientResumeService.addPatientResume(request);
        return CommonResult.ok();
    }

    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        patientResumeService.deletePatientResume(id);
        return CommonResult.ok();
    }

    // 和add接口共用
    @RequestMapping("/update")
    public CommonResult<Void> update(@RequestBody PatientResumeRequest request) {
        boolean updateSuccess = patientResumeService.updatePatientResume(request);

        // 如果更新不成功，执行添加操作
        if (!updateSuccess) {
            patientResumeService.addPatientResume(request);
        }

        // 返回操作成功的结果
        return CommonResult.ok();
    }

    @GetMapping("/one/{id}")
    public CommonResult<PatientResumeDto> one(@PathVariable Long id) {
        PatientResumeDto one = patientResumeService.getPatientResumeOne(id);
        return CommonResult.data(one);
    }
}
