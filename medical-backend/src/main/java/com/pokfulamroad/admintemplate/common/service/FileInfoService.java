package com.pokfulamroad.admintemplate.common.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.common.entity.FilesInfoRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.FilesInfo;

import java.util.List;
import java.util.Map;


public interface FileInfoService extends IService<FilesInfo> {

    /**
     * 获取所有图片base64并返回
     */
    List<String> getOwnerImagesUrl(Long ownerId, String type);

    List<String> getOwnerFileBase64(Long ownerId, String type);
    /**
     * 删除所有后并重新保存
     */
    void deleteOwnerImagesAndReSave(Long ownerId, String type, List<FilesInfoRequest> request);
    /**
     * 删除全部
     */
    void deleteOwnerImages(Long ownerId, String type);

    void saveOwnerImages(Long ownerId, String type, List<FilesInfoRequest> request);
    void saveOwnerFilesBase64(Long ownerId, String type, List<String> request);

    Map<Long, List<String>> getOwnerListFiles(List<Long> ownerId, String type);
    /**
     * 给ownerPageList设置图片url
     */
    <T> void setOwnerListImagesUrl(List<T> ownerPageList, String type);
    <T> void setOwnerListFilesBase64(List<T> ownerPageList, String type);


}
