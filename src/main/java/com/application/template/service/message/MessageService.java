package com.application.template.service.message;

import com.application.template.entity.appUser.auth.CaptchaAuthAccessWay;

public interface MessageService {
    void sendCaptchaMessage(CaptchaAuthAccessWay accessWay, String text, String captcha);
}
