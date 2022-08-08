package com.application.template.service.message;

import com.application.template.entity.appUser.auth.RegisterBody;

public interface MessageService {
    String sendCaptchaMessage(RegisterBody registerBody, String text);
}
