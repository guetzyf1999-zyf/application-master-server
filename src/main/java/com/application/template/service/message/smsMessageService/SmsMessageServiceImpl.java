package com.application.template.service.message.smsMessageService;

import com.application.template.config.applicationProps.SmsMessageConfigProps;
import com.application.template.constant.ExternalServiceAddress;
import com.application.template.dto.login.CaptchaAuthAccessWay;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service("smsMessageServiceImpl")
public class SmsMessageServiceImpl implements SmsMessageService {

    @Autowired
    private SmsMessageConfigProps smsMessageConfigProps;

    @Override
    public void sendCaptchaMessage(CaptchaAuthAccessWay accessWay, String smsText, String captcha) {
        AppAssert.judge(!StringUtils.hasText(accessWay.getReceivingId()),new AppException("请先填写电话!"));
        Map<String, Object> params = new HashMap<>();
        params.put("Key", smsMessageConfigProps.getKey());
        params.put("Uid", smsMessageConfigProps.getUid());
        params.put("smsMob", accessWay.getReceivingId());
        params.put("smsText", smsText + captcha);
        HttpUtil.httpGetWithParams(ExternalServiceAddress.SMS_MESSAGE_HOST, params);
    }
}
