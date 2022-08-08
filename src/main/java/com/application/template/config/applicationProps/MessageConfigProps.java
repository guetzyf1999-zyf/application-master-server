package com.application.template.config.applicationProps;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("captcha")
public class MessageConfigProps {
    private String captchasendingpath;

    public String getCaptchasendingpath() {
        return captchasendingpath;
    }

    public void setCaptchasendingpath(String captchasendingpath) {
        this.captchasendingpath = captchasendingpath;
    }
}
