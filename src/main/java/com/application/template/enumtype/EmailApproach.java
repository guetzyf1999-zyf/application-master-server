package com.application.template.enumtype;

import com.application.template.service.message.emailMessageService.EmailMessageService;
import com.application.template.service.message.emailMessageService.DefaultEmailServiceImpl;

public enum EmailApproach {
    DEFAULT_EMAIL("邮箱", 1, DefaultEmailServiceImpl.class);

    private final String approachName;
    private final int index;
    private final Class<? extends EmailMessageService> emailMessageServiceClass;

    EmailApproach(String approachName, int index, Class<? extends EmailMessageService> emailMessageServiceClass) {
        this.approachName = approachName;
        this.index = index;
        this.emailMessageServiceClass = emailMessageServiceClass;
    }

    public String getApproachName() {
        return approachName;
    }

    public int getIndex() {
        return index;
    }

    public Class<? extends EmailMessageService> getEmailMessageServiceClass() {
        return emailMessageServiceClass;
    }
}
