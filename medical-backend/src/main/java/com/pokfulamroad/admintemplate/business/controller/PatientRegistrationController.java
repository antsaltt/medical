// 处理patientRegistration的增删改查
package com.pokfulamroad.admintemplate.business.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationDto;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationRequest;
import com.pokfulamroad.admintemplate.business.service.PatientRegistrationService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patientRegistration")
@CrossOrigin
public class PatientRegistrationController {

    private final PatientRegistrationService patientRegistrationService;

    @PostMapping("/doReg")
    public CommonResult<Void> save(@RequestBody PatientRegistrationRequest request) {
        patientRegistrationService.addPatientRegistration(request);
        return CommonResult.ok();
    }

    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        patientRegistrationService.deletePatientRegistration(id);
        return CommonResult.ok();
    }


    @PostMapping("/page")
    public CommonResult<Page<PatientRegistrationDto>> page(@RequestBody PatientRegistrationRequest request) {
        Page<PatientRegistrationDto> patientRegistrationPage = patientRegistrationService.getPatientRegistrationPage(request);
        return CommonResult.data(patientRegistrationPage);
    }


    @GetMapping("/one/{id}")
    public CommonResult<PatientRegistrationDto> saveMenu(@PathVariable Long id) {
        PatientRegistrationDto patientRegistrationOne = patientRegistrationService.getPatientRegistrationOne(id);
        return CommonResult.data(patientRegistrationOne);
    }

    @GetMapping("/patient/regList")
    public CommonResult<List<PatientRegistrationDto>> patientRegList() {
        List<PatientRegistrationDto> list = patientRegistrationService.getPatientRegList();
        return CommonResult.data(list);
    }
}
