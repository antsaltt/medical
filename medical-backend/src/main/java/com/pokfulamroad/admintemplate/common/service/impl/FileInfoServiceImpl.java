package com.pokfulamroad.admintemplate.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.common.entity.FilesInfoRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.FilesInfo;
import com.pokfulamroad.admintemplate.common.mapper.FilesInfoMapper;
import com.pokfulamroad.admintemplate.common.service.FileInfoService;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class FileInfoServiceImpl extends ServiceImpl<FilesInfoMapper, FilesInfo>
        implements FileInfoService {

    @Override
    public void saveOwnerImages(Long ownerId, String type, List<FilesInfoRequest> request) {
        if (CollUtil.isEmpty(request)) return;

        List<FilesInfo> infoList = request.stream().map(x -> {
            FilesInfo filesInfo = new FilesInfo();
            filesInfo.setOwnerId(ownerId);
            filesInfo.setBase64Content(x.getBase64Content());

            filesInfo.setType(type);
            filesInfo.setCreateTime(new Date());
            return filesInfo;
        }).toList();

        infoList.forEach(this.baseMapper::insert);
    }

    @Override
    public void saveOwnerFilesBase64(Long ownerId, String type, List<String> request) {
        if (CollUtil.isEmpty(request)) return;
        List<FilesInfo> infoList = request.stream().map(x -> {
            FilesInfo filesInfo = new FilesInfo();
            filesInfo.setOwnerId(ownerId);
            filesInfo.setBase64Content(x);

            filesInfo.setType(type);
            filesInfo.setCreateTime(new Date());
            return filesInfo;
        }).toList();

        infoList.forEach(this.baseMapper::insert);
    }

    @Override
    public void deleteOwnerImages(Long ownerId, String type) {
        LambdaQueryWrapper<FilesInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilesInfo::getOwnerId, ownerId);
        queryWrapper.eq(FilesInfo::getType, type);
        this.baseMapper.delete(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOwnerImagesAndReSave(Long ownerId, String type, List<FilesInfoRequest> requests) {
        if (CollUtil.isEmpty(requests)) {
            this.deleteOwnerImages(ownerId, type);
            return;
        }
        LambdaQueryWrapper<FilesInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilesInfo::getOwnerId, ownerId);
        queryWrapper.eq(FilesInfo::getType, type);
        this.baseMapper.delete(queryWrapper);
        this.saveOwnerImages(ownerId, type, requests);

    }

    @Override
    public List<String> getOwnerImagesUrl(Long ownerId, String type) {

        LambdaQueryWrapper<FilesInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilesInfo::getOwnerId, ownerId);
        queryWrapper.eq(FilesInfo::getType, type);
        List<FilesInfo> imagesInfos = this.baseMapper.selectList(queryWrapper);

        if (CollUtil.isEmpty(imagesInfos)) return Collections.emptyList();

        return imagesInfos.stream().map(FilesInfo::getUrl).toList();
    }

    @Override
    public List<String> getOwnerFileBase64(Long ownerId, String type) {
        LambdaQueryWrapper<FilesInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilesInfo::getType, type);
        queryWrapper.in(FilesInfo::getOwnerId, ownerId);

        List<FilesInfo> filesInfos = this.baseMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(filesInfos)) throw new ServiceException("未上传文件");

        return filesInfos.stream().map(FilesInfo::getBase64Content).toList();
    }

    @Override
    public <T> void setOwnerListFilesBase64(List<T> ownerPageList, String type) {
        List<Long> ownerIds = ownerPageList.stream().map(x -> Long.parseLong((String) ReflectUtil.getFieldValue(x, "id"))).toList();
        Map<Long, List<String>> ownerListImagesUrl = this.getOwnerListFiles(ownerIds, type);

        ownerPageList.forEach(x -> {
            Long ownerId = Long.parseLong((String) ReflectUtil.getFieldValue(x, "id"));
            List<String> fileBase64 = ownerListImagesUrl.get(ownerId);
            ReflectUtil.setFieldValue(x, "files", fileBase64);
        });
    }

    @Override
    public <T> void setOwnerListImagesUrl(List<T> ownerPageList, String type) {
        List<Long> ownerIds = ownerPageList.stream().map(x -> Long.parseLong((String) ReflectUtil.getFieldValue(x, "id"))).toList();
        Map<Long, List<String>> ownerListImagesUrl = this.getOwnerListFiles(ownerIds, type);

        ownerPageList.forEach(x -> {
            Long ownerId = Long.parseLong((String) ReflectUtil.getFieldValue(x, "id"));
            List<String> imagesUrl = ownerListImagesUrl.get(ownerId);
            ReflectUtil.setFieldValue(x, "filesUrl", imagesUrl);
        });
    }

    @Override
    public Map<Long, List<String>> getOwnerListFiles(List<Long> ownerId, String type) {
        LambdaQueryWrapper<FilesInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilesInfo::getType, type);
        queryWrapper.in(FilesInfo::getOwnerId, ownerId);

        List<FilesInfo> imagesInfos = this.baseMapper.selectList(queryWrapper);
        return imagesInfos.stream().collect(Collectors.groupingBy(FilesInfo::getOwnerId,
                Collectors.mapping(FilesInfo::getBase64Content, Collectors.toList())));
    }
}




