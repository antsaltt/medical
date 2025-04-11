package com.pokfulamroad.admintemplate.business.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="department_info")
@Data
public class DepartmentInfo  {

    @TableId
    private Long id;

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
    private Date createTime;


}
