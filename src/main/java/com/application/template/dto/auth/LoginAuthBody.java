package com.application.template.dto.auth;

import com.application.template.enumtype.LoginAuthWay;

public class LoginAuthBody {
    private String verifyId;
    private String verifyCode;
    private boolean remember;
    private Integer loginAuthWay;

    public LoginAuthBody() {}

    public LoginAuthBody(String phone) {
        this.verifyId = phone;
        this.loginAuthWay = LoginAuthWay.PHONE.getIndex();
        this.remember = true;
        this.verifyCode = "";
    }

    public String getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(String verifyId) {
        this.verifyId = verifyId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public Integer getLoginAuthWay() {
        return loginAuthWay;
    }

    public void setLoginAuthWay(Integer loginAuthWay) {
        this.loginAuthWay = loginAuthWay;
    }
}
