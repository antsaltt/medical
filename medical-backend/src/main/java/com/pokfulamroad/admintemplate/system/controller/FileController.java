package com.pokfulamroad.admintemplate.system.controller;


import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import com.pokfulamroad.admintemplate.system.entity.FileDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/sys/common/file")
public class FileController {

    @PostMapping("/uploadImage")
    public CommonResult<FileDto> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String uuidStr = UUID.randomUUID().toString();
        String newFileName = uuidStr + "." + extension;
        String dateFolderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Path path = Paths.get("./images/" + dateFolderName + "/" + newFileName);
        Files.createDirectories(path.getParent());
        Files.write(path, bytes);
        FileDto fileDto = new FileDto();
        String returnUrl = "/images/" + dateFolderName + "/" + newFileName;
        fileDto.setUrl(returnUrl);
        fileDto.setFileName(originalFileName);
        fileDto.setUid(uuidStr);
        return CommonResult.data(fileDto);
    }
}
