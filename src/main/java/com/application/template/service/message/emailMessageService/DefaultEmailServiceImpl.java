package com.application.template.service.message.emailMessageService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.factory.MessageSendingServiceFactory;
import com.application.template.service.message.MessageService;
import com.application.template.util.EmailUtil;

@Service
public class DefaultEmailServiceImpl implements MessageService, InitializingBean {

    @Value("${mailservice.sender}")
    private String myEmailAddress;

    @Override
    @TimeCount(name = "sendEmailCaptchaMessage")
    public void sendCaptchaMessage(String title, String email, String text, String captcha) {
        AppAssert.judge(!StringUtils.hasText(email),new AppException("请先填写邮箱!"));
        EmailUtil.sendEmail(title, text + captcha, email, myEmailAddress);
    }

    @Override
    public void afterPropertiesSet() {
        MessageSendingServiceFactory.putMessageService(MessageSendingApproach.EMAIL, this);
    }
}
