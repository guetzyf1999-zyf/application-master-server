package com.application.template.service.message.smsMessageService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.config.applicationProps.SmsMessageConfigProps;
import com.application.template.constant.ExternalServiceAddress;
import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.factory.MessageSendingServiceFactory;
import com.application.template.service.message.MessageService;
import com.application.template.util.HttpUtil;

@Service("smsMessageServiceImpl")
public class SmsMessageServiceImpl implements MessageService, InitializingBean {

    @Autowired
    private SmsMessageConfigProps smsMessageConfigProps;

    @Override
    @TimeCount(name = "sendSMSCaptchaMessage")
    public void sendCaptchaMessage(String phone, String smsText, String captcha) {
        AppAssert.judge(!StringUtils.hasText(phone),new AppException("请先填写电话!"));
        Map<String, Object> params = new HashMap<>();
        params.put("Key", smsMessageConfigProps.getKey());
        params.put("Uid", smsMessageConfigProps.getUid());
        params.put("smsMob", phone);
        params.put("smsText", smsText + captcha);
        HttpUtil.httpGetWithParams(ExternalServiceAddress.SMS_MESSAGE_HOST, params);
    }

    @Override
    public void afterPropertiesSet() {
        MessageSendingServiceFactory.putMessageService(MessageSendingApproach.SMS, this);
    }
}
