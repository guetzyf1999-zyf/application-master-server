package com.application.template.dto.login;

public class CaptchaAuthAccessWay {
    private String authWay;
    private String receivingId;

    public CaptchaAuthAccessWay(){}

    public CaptchaAuthAccessWay(String authWay, String receivingId) {
        this.authWay = authWay;
        this.receivingId = receivingId;
    }

    public String getAuthWay() {
        return authWay;
    }

    public void setAuthWay(String authWay) {
        this.authWay = authWay;
    }

    public String getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(String receivingId) {
        this.receivingId = receivingId;
    }
}
