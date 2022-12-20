package com.application.template.dto.auth;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class ResetPasswordParamsDTO {
    private String receivingId;
    private String captchaCode;
    private String newPassword;
    private Integer captchaPrefix;

    public String genSaltForNewPassword() {
        return BCrypt.hashpw(newPassword, BCrypt.gensalt());
    }

    public String getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(String receivingId) {
        this.receivingId = receivingId;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getCaptchaPrefix() {
        return captchaPrefix;
    }

    public void setCaptchaPrefix(Integer captchaPrefix) {
        this.captchaPrefix = captchaPrefix;
    }
}
