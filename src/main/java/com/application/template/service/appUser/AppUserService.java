package com.application.template.service.appUser;

import com.application.template.dto.auth.AuthBody;
import com.application.template.dto.login.CaptchaAuthAccessWay;
import com.application.template.dto.login.CaptchaAuthDTO;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.dto.login.RegisterBody;
import com.application.template.entity.appUser.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    JwtAuthResponseBody login(AuthBody authBody);

    CaptchaAuthDTO getCaptchaCode(CaptchaAuthAccessWay accessWay);

    AppUser register(RegisterBody registerBody);
}
