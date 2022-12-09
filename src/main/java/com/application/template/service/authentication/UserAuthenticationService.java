package com.application.template.service.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthenticationService extends UserDetailsService {

	void getCaptchaCode(Integer captchaKeyPrefixIndex, String receivingId);

	void checkCaptchaCode(String captchaKey,String captcha);

}
