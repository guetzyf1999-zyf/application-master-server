package com.application.template.service.message;

public interface MessageService {
    void sendCaptchaMessage(String title, String receivingId, String text, String captcha);
}
