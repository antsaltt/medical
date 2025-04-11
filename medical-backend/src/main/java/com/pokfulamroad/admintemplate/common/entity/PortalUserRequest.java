package com.pokfulamroad.admintemplate.common.entity;

import com.pokfulamroad.admintemplate.system.entity.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PortalUserRequest extends BaseRequest {

    private String username;

    private String password;

    private String phoneNum;

}
