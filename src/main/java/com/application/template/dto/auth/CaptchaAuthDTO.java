package com.application.template.dto.auth;

public class CaptchaAuthDTO {
    private String captcha;
    private long effectiveTime;

    public CaptchaAuthDTO(String captcha, long effectiveTime) {
        this.captcha = captcha;
        this.effectiveTime = effectiveTime;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}
