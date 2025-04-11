package com.pokfulamroad.admintemplate.system.auth.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.pokfulamroad.admintemplate.system.auth.entity.SysLoginUser;

public class StpLoginUserUtil {

    public static SysLoginUser getLoginUser() {
        return (SysLoginUser) StpUtil.getTokenSession().get("loginUser");
    }
}
