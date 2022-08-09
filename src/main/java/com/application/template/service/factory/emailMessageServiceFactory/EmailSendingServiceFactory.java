package com.application.template.service.factory.emailMessageServiceFactory;

import com.application.template.config.applicationProps.EmailMessageServiceConfigProps;
import com.application.template.enumtype.EmailApproach;
import com.application.template.service.factory.MessageSendingServiceFactory;
import com.application.template.service.message.MessageService;
import com.application.template.util.SpringUtil;

public interface EmailSendingServiceFactory extends MessageSendingServiceFactory {

    default MessageService getMessageService() {
        EmailMessageServiceConfigProps platformProps = SpringUtil.getBean(EmailMessageServiceConfigProps.class);
        return SpringUtil.getBean(EmailApproach.valueOf(platformProps.getEmailplatform()).getEmailMessageServiceClass());
    }
}
