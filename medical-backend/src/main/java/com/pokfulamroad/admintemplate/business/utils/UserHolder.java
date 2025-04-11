package com.pokfulamroad.admintemplate.business.utils;


import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;

public class UserHolder {
    private static final ThreadLocal<UserAccountDto> tl = new ThreadLocal<>();

    public static void saveUser(UserAccountDto user) {
        tl.set(user);
    }

    public static UserAccountDto getUser() {
        return tl.get();
    }

    public static void removeUser() {
        tl.remove();
    }
}
