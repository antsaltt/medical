package com.pokfulamroad.admintemplate.business.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="hospital_info")
@Data
public class HospitalInfo  {

    @TableId
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
    private Date createTime;


}
