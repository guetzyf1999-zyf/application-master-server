package com.application.template.service.authentication;

import com.application.template.dto.auth.CaptchaAuthAccessWay;
import com.application.template.dto.auth.CaptchaAuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthenticationService extends UserDetailsService {
    CaptchaAuthDTO getCaptchaCode(CaptchaAuthAccessWay authAccessWay);
}
