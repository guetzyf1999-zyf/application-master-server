package com.application.template.service.factory;

import com.application.template.service.message.MessageService;

public interface MessageSendingServiceFactory {
    MessageService getMessageService();
}
