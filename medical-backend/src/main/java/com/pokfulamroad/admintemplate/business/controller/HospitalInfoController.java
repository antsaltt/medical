// 处理hospitalInfo的增删改查
package com.pokfulamroad.admintemplate.business.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoDto;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoRequest;
import com.pokfulamroad.admintemplate.business.service.HospitalInfoService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitalInfo")
@CrossOrigin
public class HospitalInfoController {

    private final HospitalInfoService hospitalInfoService;

    @PostMapping("/save")
    public CommonResult<Void> save(@RequestBody HospitalInfoRequest request) {
        hospitalInfoService.addHospitalInfo(request);
        return CommonResult.ok();
    }

    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        hospitalInfoService.deleteHospitalInfo(id);
        return CommonResult.ok();
    }

    @PostMapping("/update")
    public CommonResult<Void> updateHospitalInfo(@RequestBody HospitalInfoRequest request) {
        hospitalInfoService.updateHospitalInfo(request);
        return CommonResult.ok();
    }

    @PostMapping("/page")
    public CommonResult<Page<HospitalInfoDto>> page(@RequestBody HospitalInfoRequest request) {
        Page<HospitalInfoDto> hospitalInfoPage = hospitalInfoService.getHospitalInfoPage(request);
        return CommonResult.data(hospitalInfoPage);
    }

    @PostMapping("/list")
    public CommonResult<List<HospitalInfoDto>> list(@RequestBody HospitalInfoRequest request) {
        List<HospitalInfoDto> hospitalInfoPage = hospitalInfoService.getHospitalInfoList(request);
        return CommonResult.data(hospitalInfoPage);
    }


    @GetMapping("/one/{id}")
    public CommonResult<HospitalInfoDto> saveMenu(@PathVariable Long id) {
        HospitalInfoDto hospitalInfoOne = hospitalInfoService.getHospitalInfoOne(id);
        return CommonResult.data(hospitalInfoOne);
    }
}
