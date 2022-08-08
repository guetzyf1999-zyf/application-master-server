package com.application.template.service.factory;

import com.application.template.config.applicationProps.SmsMessageServiceConfigProps;
import com.application.template.enumtype.SmsApproach;
import com.application.template.service.message.MessageService;
import com.application.template.util.SpringUtil;

public interface SmsSendingServiceFactory extends MessageSendingServiceFactory {
    default MessageService getMessageService() {
        SmsMessageServiceConfigProps platformProps = SpringUtil.getBean(SmsMessageServiceConfigProps.class);
        return SpringUtil.getBean(SmsApproach.valueOf(platformProps.getSmsplatform()).getMessageServiceClass());
    }
}
