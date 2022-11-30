package com.application.template.enumtype;

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

    public int getIndex() {
        return index;
    }
}
