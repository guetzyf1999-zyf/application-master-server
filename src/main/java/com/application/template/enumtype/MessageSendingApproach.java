package com.application.template.enumtype;

import com.application.template.service.factory.*;
import com.application.template.service.factory.SmsMessageServiceFactory.SmsSendingServiceFactoryImpl;
import com.application.template.service.factory.emailMessageServiceFactory.EmailSendingServiceFactoryImpl;

//验证码发送途径
public enum MessageSendingApproach {
    //email发送验证码
    EMAIL("email", 1, EmailSendingServiceFactoryImpl.class),
    //短信平台发送验证码
    SMS("phone", 1, SmsSendingServiceFactoryImpl.class);

    private final String approachName;
    private final int index;
    private final Class<? extends MessageSendingServiceFactory> messageServiceFactoryClass;

    MessageSendingApproach(String approachName, int index, Class<? extends MessageSendingServiceFactory> messageServiceFactoryClass) {
         this.approachName = approachName;
         this.index = index;
         this.messageServiceFactoryClass = messageServiceFactoryClass;
    }

    public String getApproachName() {
        return approachName;
    }

    public int getIndex() {
        return index;
    }

    public Class<? extends MessageSendingServiceFactory> getMessageServiceFactoryClass (){
        return messageServiceFactoryClass;
    }
}
