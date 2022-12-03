package com.application.template.factory;

import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.service.message.MessageService;
import com.application.template.util.SpringUtil;

import java.util.EnumMap;
import java.util.Map;

public class MessageSendingServiceFactory {

    private static final Map<MessageSendingApproach, MessageService> SENDING_MESSAGE_SERVICE_FACTORY = new EnumMap<>(MessageSendingApproach.class);

    private MessageSendingServiceFactory() {
        throw new UnsupportedOperationException("工厂禁止初始化");
    }

    public static MessageService getMessageService(MessageSendingApproach approach) {
        return SpringUtil.getBean(SENDING_MESSAGE_SERVICE_FACTORY.get(approach).getClass());
    }

    public static void putMessageService(MessageSendingApproach approach,MessageService messageService) {
        SENDING_MESSAGE_SERVICE_FACTORY.put(approach, messageService);
    }
}
