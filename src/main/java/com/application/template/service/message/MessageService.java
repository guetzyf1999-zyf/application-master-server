package com.application.template.service.message;

public interface MessageService {
    void sendCaptchaMessage(String receivingId, String text, String captcha);
}
