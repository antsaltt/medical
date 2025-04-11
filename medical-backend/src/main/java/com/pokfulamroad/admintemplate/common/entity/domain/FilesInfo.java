package com.pokfulamroad.admintemplate.common.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName(value ="files_info")
@Data
public class FilesInfo implements Serializable {

    @TableId
    private Long id;

    private Long ownerId;

    private String url;

    private String base64Content;

    private String type;

    private Date createTime;

}
