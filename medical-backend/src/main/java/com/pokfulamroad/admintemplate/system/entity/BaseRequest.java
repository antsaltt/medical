package com.pokfulamroad.admintemplate.system.entity;

import lombok.Data;

@Data
public class BaseRequest {

    private Integer pageSize = 10;

    private Integer current = 1;

    private Integer limit;
}
