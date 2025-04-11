package com.pokfulamroad.admintemplate.common.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 发帖用户ID
     */
    private Long userId;

    /**
     * 帖子标题
     */
    private String title;

    private String previewContent;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 发帖时间
     */
    private Date createTime;

}
