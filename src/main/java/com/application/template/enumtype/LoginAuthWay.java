package com.application.template.enumtype;

import com.application.template.exceptionHandle.exception.AppException;

import java.util.Arrays;

public enum LoginAuthWay {
    USERNAME("账号", 10),
    PHONE("电话", 20),
    EMAIL("邮箱", 30);

    LoginAuthWay(String authWay, int index) {
        this.authWay = authWay;
        this.index = index;
    }

    private final String authWay;
    private final Integer index;

    public String getAuthWay() {
        return authWay;
    }

    public static LoginAuthWay getLoginAuthWayByIndex(Integer index) {
        return Arrays.stream(LoginAuthWay.values()).filter(auth -> auth.index.equals(index))
                .findFirst().orElseThrow(() -> new AppException("找不到认证方式"));
    }

    public int getIndex() {
        return index;
    }
}
