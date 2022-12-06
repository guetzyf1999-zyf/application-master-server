package com.application.template.service.authentication;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.dto.auth.AuthBody;
import com.application.template.dto.auth.CaptchaAuthAccessWay;
import com.application.template.dto.auth.CaptchaAuthDTO;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.factory.MessageSendingServiceFactory;
import com.application.template.factory.UserLoadServiceFactory;
import com.application.template.service.message.MessageService;
import com.application.template.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    @Value("${captcha.effectivetime}")
    private long effectiveTime;

    @Value("${captcha.messagetemplate}")
    private String messageTemplate;

    @Override
    public UserDetails loadUserByUsername(String authJson) {
        AuthBody authBody = JsonUtil.toObject(authJson, AuthBody.class);
        return UserLoadServiceFactory.getUserLoadService(LoginAuthWay.getLoginAuthWayByIndex(authBody.getAuthWay()))
                .loadUserByUsername(authBody);
    }

    @Override
    @TimeCount(name = "getCaptchaCode")
    public CaptchaAuthDTO getCaptchaCode(CaptchaAuthAccessWay accessWay) {
        MessageSendingApproach approach = MessageSendingApproach.valueOf(accessWay.getAuthWay());
        String captcha = String.valueOf(new Random().nextInt(999999) % (999999 - 100000 + 1) + 100000);
        MessageService messageService = MessageSendingServiceFactory.getMessageService(approach);
        messageService.sendCaptchaMessage(accessWay, genMessageTemplate(), captcha);
        return new CaptchaAuthDTO(captcha, effectiveTime);
    }

    private String genMessageTemplate() {
        long minutes = effectiveTime / 1000;
        return messageTemplate + minutes + "秒: ";
    }
}
