package com.pokfulamroad.admintemplate.business.entity;

import com.pokfulamroad.admintemplate.system.entity.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentInfoRequest extends BaseRequest {

    private Long id;

    /**
    * 科室名称
    */
    private String name;




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
