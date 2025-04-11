package com.pokfulamroad.admintemplate.common.entity;

import lombok.Data;

@Data
public class FilesInfoRequest {

    private Long id;

    private String fileName;

    private Long ownerId;

    private String base64Content;

    private String url;

    private String type;
}
