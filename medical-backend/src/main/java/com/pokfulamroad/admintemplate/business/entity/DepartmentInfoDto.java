package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


@Data
public class DepartmentInfoDto {

    private String id;

    /**
    * 科室名称
    */
    private String name;

    private String icon;


    /**
    * 科室描述
    */
    private String description;


    /**
    * 状态 1:正常 2:停用
    */
    private String status;


    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
