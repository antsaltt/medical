package com.pokfulamroad.admintemplate.business.entity;

import com.pokfulamroad.admintemplate.system.entity.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class HospitalInfoRequest extends BaseRequest {

    private Long id;

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
