package com.application.template.service.authentication;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.enumtype.CaptchaKeyPrefix;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.factory.MessageSendingServiceFactory;
import com.application.template.service.message.MessageService;
import com.application.template.util.RedisUtil;
import com.application.template.util.SpringUtil;

@Service
@Transactional
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    @Value("${captcha.effectivetime}")
    private long effectiveTime;

    @Value("${captcha.messagetemplate}")
    private String messageTemplate;

    @Override
    @TimeCount(name = "getCaptchaCode")
    public void getCaptchaCode(Integer captchaKeyPrefixIndex, String receivingId) {
        CaptchaKeyPrefix prefix = CaptchaKeyPrefix.getCaptchaKeyPrefixByIndex(captchaKeyPrefixIndex);
        String captchaKey = prefix.getPrefix() + receivingId;
        logger.info("用户:{}正在获取验证码", receivingId);
        String captcha = String.valueOf(new Random().nextInt(999999) % (999999 - 100000 + 1) + 100000);
        MessageService messageService = MessageSendingServiceFactory.getMessageService(prefix.getMessageSendingApproach());
        messageService.sendCaptchaMessage(receivingId, genMessageTemplate(), captcha);
        SpringUtil.getBean(RedisUtil.class).insertStr(captchaKey, captcha, prefix.getTimeout(), prefix.getUnit());
    }

    @Override
    public void checkCaptchaCode(String captchaKey,String captcha) {
        String captchaCode = SpringUtil.getBean(RedisUtil.class).getStr(captchaKey);
        AppAssert.judge(!StringUtils.hasText(captchaCode), new AppException("验证码已失效,请重新获取"));
        AppAssert.judge(!captcha.equals(captchaCode), new AppException("验证码错误!"));
    }

    private String genMessageTemplate() {
        long minutes = effectiveTime / 1000;
        return messageTemplate + minutes + "秒: ";
    }
}
