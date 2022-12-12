package com.application.template.service.authentication;

public interface UserAuthenticationService {

	void getCaptchaCode(Integer captchaKeyPrefixIndex, String receivingId);

	void checkCaptchaCode(String captchaKey,String captcha);

	void checkCaptchaCodeAndDelete(String captchaKey,String captcha);

	void deleteCaptchaCode(String captchaKey);

}
