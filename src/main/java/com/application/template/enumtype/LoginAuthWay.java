package com.application.template.enumtype;

import java.util.Arrays;

import com.application.template.exceptionHandle.exception.AppException;

public enum LoginAuthWay {
    PASSWORD("密码", 10, null),
    PHONE("电话", 20, CaptchaKeyPrefix.LOGIN_BY_PHONE),
    EMAIL("邮箱", 30, CaptchaKeyPrefix.LOGIN_BY_EMAIL);

    LoginAuthWay(String authWay, int index, CaptchaKeyPrefix prefix) {
        this.authWay = authWay;
        this.index = index;
        this.prefix = prefix;
    }

    private final String authWay;
    private final Integer index;
    private final CaptchaKeyPrefix prefix;

    public static LoginAuthWay getLoginAuthWayByIndex(Integer index) {
        return Arrays.stream(LoginAuthWay.values()).filter(auth -> auth.index.equals(index))
                .findFirst().orElseThrow(() -> new AppException("找不到认证方式"));
    }

    public String getAuthWay() {
        return authWay;
    }

    public int getIndex() {
        return index;
    }

    public CaptchaKeyPrefix getPrefix() {
        return prefix;
    }
}
