package com.pokfulamroad.admintemplate.common.entity;


import lombok.Data;

import java.util.Date;

@Data
public class PostDto {

    private String id;
    /**
     * 发帖用户ID
     */
    private String userId;

    private String username;

    private String docName;
    private String hosName;

    private String avatar;

    private Integer commentNum;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 帖子内容
     */
    private String content;

    private String previewContent;

    private Date createTime;
}
