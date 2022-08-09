package com.application.template.service.message.emailMessageService;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.entity.appUser.auth.RegisterBody;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class DefaultEmailServiceImpl implements EmailMessageService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEmailServiceImpl.class);

    @Value("${mailservice.sender}")
    private String myEmailAddress;

    @Value("${captcha.effectivetime}")
    private long effectiveTime;

    @Override
    @TimeCount(name = "sendCaptchaMessage")
    public String sendCaptchaMessage(RegisterBody registerBody, String text) {
        AppAssert.judge(!StringUtils.hasText(registerBody.getEmail()),new AppException("请先填写邮箱!"));
        String captcha = String.valueOf(new Random().nextInt(999999) % (999999 - 100000 + 1) + 100000);
        EmailUtil.sendEmail("新用户注册验证码", text + captcha, registerBody.getEmail(), myEmailAddress);
        String logInfo = "用户" + registerBody.getEmail() + "验证码有效时长" + effectiveTime / 1000 + "s";
        logger.info(logInfo);
        return captcha;
    }
}
