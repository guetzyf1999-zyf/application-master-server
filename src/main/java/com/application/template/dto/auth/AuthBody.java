package com.application.template.dto.auth;

public class AuthBody {
    private String verifyCredentials;
    private String password;
    private boolean remember;
    private Integer authWay;

    public String getVerifyCredentials() {
        return verifyCredentials;
    }

    public void setVerifyCredentials(String verifyCredentials) {
        this.verifyCredentials = verifyCredentials;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public Integer getAuthWay() {
        return authWay;
    }

    public void setAuthWay(Integer authWay) {
        this.authWay = authWay;
    }
}
