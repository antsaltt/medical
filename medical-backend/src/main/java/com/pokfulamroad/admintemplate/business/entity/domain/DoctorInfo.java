package com.pokfulamroad.admintemplate.business.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="doctor_info")
@Data
public class DoctorInfo  {

    @TableId
    private Long id;

    /**
    * 医生姓名
    */
    private String name;

    private String description;

    /**
    * 性别 1:男 2:女
    */
    private String gender;

    /**
    * 出生日期
    */
    private Date birthday;

    private String avatar;

    /**
    * 职称
    */
    private String title;

    /**
    * 所属科室ID
    */
    private Long departmentId;

    /**
    * 状态 1:正常 2:离职 3:休假
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
     * 评分
     */
    private Float rate;
}
