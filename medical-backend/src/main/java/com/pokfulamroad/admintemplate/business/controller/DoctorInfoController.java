package com.pokfulamroad.admintemplate.business.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.UserRateRequest;
import com.pokfulamroad.admintemplate.business.service.DoctorInfoService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctorInfo")
@CrossOrigin
public class DoctorInfoController {

    private final DoctorInfoService doctorInfoService;



    @PostMapping("/save")
    public CommonResult<Void> save(@RequestBody DoctorInfoRequest request) {
        doctorInfoService.addDoctorInfo(request);
        return CommonResult.ok();
    }
    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        doctorInfoService.deleteDoctorInfo(id);
        return CommonResult.ok();
    }
    @PostMapping("/update")
    public CommonResult<Void> updateDoctorInfo(@RequestBody DoctorInfoRequest request) {
        doctorInfoService.updateDoctorInfo(request);
        return CommonResult.ok();
    }
    @PostMapping("/page")
    public CommonResult<Page<DoctorInfoDto>> page(@RequestBody DoctorInfoRequest request) {
        Page<DoctorInfoDto> doctorInfoPage = doctorInfoService.getDoctorInfoPage(request);
        return CommonResult.data(doctorInfoPage);
    }
    @GetMapping("/one/{id}")
    public CommonResult<DoctorInfoDto> saveMenu(@PathVariable Long id) {
        DoctorInfoDto doctorInfoOne = doctorInfoService.getDoctorInfoOne(id);
        return CommonResult.data(doctorInfoOne);
    }

    @PostMapping("/rate/add")
    public CommonResult<Void> rate(@RequestBody UserRateRequest userRateRequest) {
        if (ObjectUtil.isEmpty(userRateRequest.getRate())) {
            throw new ServiceException("请评分");
        }
        if (ObjectUtil.isEmpty(userRateRequest.getDoctorId())) {
            throw new ServiceException("请选择医生");
        }

         doctorInfoService.saveUserRate(userRateRequest);
        return CommonResult.ok();
    }

    @GetMapping("/rate/list")
    public CommonResult<Set<String>> rateList() {
        Set<String> userRateList = doctorInfoService.getUserRateList();
        return CommonResult.data(userRateList);
    }

}
