package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


@Data
public class HospitalInfoDto {

    private String id;

    /**
    * 医院名称
    */
    private String name;


    /**
    * 医院地址
    */
    private String address;


    /**
    * 联系电话
    */
    private String tel;


    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
