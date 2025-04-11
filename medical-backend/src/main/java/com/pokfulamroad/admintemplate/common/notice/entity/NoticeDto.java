package com.pokfulamroad.admintemplate.common.notice.entity;

import lombok.Data;

import java.util.Date;


@Data
public class NoticeDto {

    private String id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;
}
