// 处理patientInfo的增删改查
package com.pokfulamroad.admintemplate.business.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoDto;
import com.pokfulamroad.admintemplate.business.entity.PatientInfoRequest;
import com.pokfulamroad.admintemplate.business.service.PatientInfoService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patientInfo")
public class PatientInfoController {

    private final PatientInfoService patientInfoService;

    @PostMapping("/save")
    public CommonResult<Void> save(@RequestBody PatientInfoRequest request) {
        patientInfoService.addPatientInfo(request);
        return CommonResult.ok();
    }

    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        patientInfoService.deletePatientInfo(id);
        return CommonResult.ok();
    }

    @PostMapping("/update")
    public CommonResult<Void> updatePatientInfo(@RequestBody PatientInfoRequest request) {
        patientInfoService.updatePatientInfo(request);
        return CommonResult.ok();
    }

    @PostMapping("/page")
    public CommonResult<Page<PatientInfoDto>> page(@RequestBody PatientInfoRequest request) {
        Page<PatientInfoDto> patientInfoPage = patientInfoService.getPatientInfoPage(request);
        return CommonResult.data(patientInfoPage);
    }


    @GetMapping("/one/{id}")
    public CommonResult<PatientInfoDto> saveMenu(@PathVariable Long id) {
        PatientInfoDto patientInfoOne = patientInfoService.getPatientInfoOne(id);
        return CommonResult.data(patientInfoOne);
    }
}
