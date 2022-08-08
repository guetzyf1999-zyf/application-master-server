package com.application.template.config.applicationProps;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("messageconfig.smsmessageconfig")
public class SmsMessageServiceConfigProps {
    private String smsplatform;

    public String getSmsplatform() {
        return smsplatform;
    }

    public void setSmsplatform(String smsplatform) {
        this.smsplatform = smsplatform;
    }
}
