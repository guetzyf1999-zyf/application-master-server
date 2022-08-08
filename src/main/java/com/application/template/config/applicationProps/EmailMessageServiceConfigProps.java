package com.application.template.config.applicationProps;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("messageconfig.emailmessageconfig")
public class EmailMessageServiceConfigProps {
    private String emailplatform;

    public String getEmailplatform() {
        return emailplatform;
    }

    public void setEmailplatform(String emailplatform) {
        this.emailplatform = emailplatform;
    }
}
