package com.application.template.enumtype;

import com.application.template.config.applicationProps.SmsMessageServiceConfigProps;
import com.application.template.service.message.smsMessageService.SmsMessageService;
import com.application.template.service.message.smsMessageService.SmsMessageServiceImpl;
import com.application.template.util.SpringUtil;

//短信平台服务枚举
public enum SmsApproach {
    //SMS中国网建平台
    SMS("SMS", 1, SmsMessageServiceImpl.class);

    private final String approachName;
    private final int index;
    private final Class<? extends SmsMessageService> messageServiceClass;

    SmsApproach(String approachName, int index, Class<? extends SmsMessageService> messageServiceClass) {
        this.approachName = approachName;
        this.index = index;
        this.messageServiceClass = messageServiceClass;
    }

    public SmsMessageService getSmsService() {
        SmsMessageServiceConfigProps smsProps = SpringUtil.getBean(SmsMessageServiceConfigProps.class);
        return SpringUtil.getBean(SmsApproach.valueOf(smsProps.getSmsplatform()).getMessageServiceClass());
    }

    public String getApproachName() {
        return approachName;
    }

    public int getIndex() {
        return index;
    }

    public Class<? extends SmsMessageService> getMessageServiceClass() {
        return messageServiceClass;
    }
}
