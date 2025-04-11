package com.pokfulamroad.admintemplate.common.entity;

import lombok.Data;

import java.util.Date;


@Data
public class PostCommentDto {

    private String id;

    /**
     * 帖子ID
     */
    private String postId;

    private String postTitle;

    /**
     * 评论用户ID
     */
    private String userId;
    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private Date createTime;
}
