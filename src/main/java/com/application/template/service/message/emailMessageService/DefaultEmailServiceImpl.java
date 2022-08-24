package com.application.template.service.message.emailMessageService;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.dto.login.CaptchaAuthAccessWay;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DefaultEmailServiceImpl implements EmailMessageService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEmailServiceImpl.class);

    @Value("${mailservice.sender}")
    private String myEmailAddress;

    @Value("${captcha.effectivetime}")
    private long effectiveTime;

    @Override
    @TimeCount(name = "sendCaptchaMessage")
    public void sendCaptchaMessage(CaptchaAuthAccessWay accessWay, String text, String captcha) {
        AppAssert.judge(!StringUtils.hasText(accessWay.getReceivingId()),new AppException("请先填写邮箱!"));
        EmailUtil.sendEmail("新用户注册验证码", text + captcha, accessWay.getReceivingId(), myEmailAddress);
        String logInfo = "用户" + accessWay.getReceivingId() + "验证码有效时长" + effectiveTime / 1000 + "s";
        logger.info(logInfo);
    }
}
