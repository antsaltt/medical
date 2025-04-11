package com.pokfulamroad.admintemplate.business.entity;

import lombok.Data;

@Data
public class UserRateRequest {

    private Long id;

    /**
     *
     */
    private Long doctorId;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Integer rate;
}
