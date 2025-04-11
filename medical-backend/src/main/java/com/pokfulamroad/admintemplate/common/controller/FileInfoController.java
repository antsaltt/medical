package com.pokfulamroad.admintemplate.common.controller;


import com.pokfulamroad.admintemplate.common.service.FileInfoService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/portal/fileInfo","/fileInfo"})
public class FileInfoController {


    private final FileInfoService fileInfoService;

    @GetMapping("/base64Str/{id}/{type}")
    public CommonResult<List<String>> getOwnerFileBase64(@PathVariable Long id, @PathVariable String type) {
        List<String> ownerFileBase64 = fileInfoService.getOwnerFileBase64(id, type);
        return CommonResult.data(ownerFileBase64);
    }
}
