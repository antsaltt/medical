package com.pokfulamroad.admintemplate.business.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="patient_info")
@Data
public class PatientInfo  {

    @TableId
    private Long id;

    /**
    * 患者姓名
    */
    private String name;

    /**
    * 性别 1:男 2:女
    */
    private String gender;

    private Integer rate;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 联系电话
    */
    private String phone;

    /**
    * 联系地址
    */
    private String address;

    /**
    * 状态 1:正常 2:停诊
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createTime;


}
