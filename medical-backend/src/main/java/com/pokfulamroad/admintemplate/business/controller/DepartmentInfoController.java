package com.pokfulamroad.admintemplate.business.controller;


import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoRequest;
import com.pokfulamroad.admintemplate.business.service.DepartmentInfoService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departmentInfo")
@CrossOrigin
public class DepartmentInfoController {

    private final DepartmentInfoService departmentInfoService;

    @PostMapping("/save")
    public CommonResult<Void> save(@RequestBody DepartmentInfoRequest request) {
        departmentInfoService.addDepartmentInfo(request);
        return CommonResult.ok();
    }

    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        departmentInfoService.deleteDepartmentInfo(id);
        return CommonResult.ok();
    }

    @PostMapping("/update")
    public CommonResult<Void> updateDepartmentInfo(@RequestBody DepartmentInfoRequest request) {
        departmentInfoService.updateDepartmentInfo(request);
        return CommonResult.ok();
    }

    @PostMapping("/list")
    public CommonResult<List<DepartmentInfoDto>> list(@RequestBody DepartmentInfoRequest request) {
        List<DepartmentInfoDto> departmentInfoPage = departmentInfoService.getDepartmentInfoList(request);
        return CommonResult.data(departmentInfoPage);
    }


    @GetMapping("/one/{id}")
    public CommonResult<DepartmentInfoDto> saveMenu(@PathVariable Long id) {
        DepartmentInfoDto departmentInfoOne = departmentInfoService.getDepartmentInfoOne(id);
        return CommonResult.data(departmentInfoOne);
    }
}
