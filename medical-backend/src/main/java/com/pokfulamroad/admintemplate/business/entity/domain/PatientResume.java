package com.pokfulamroad.admintemplate.business.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName patient_resume
 */
@TableName(value ="patient_resume")
@Data
public class PatientResume implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号码
     */
    private String idCardNo;

    /**
     * 性别
     */
    private String gender;

    /**
     * 默认选项卡 1 是 0 否
     */
    private String defaultResume;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
