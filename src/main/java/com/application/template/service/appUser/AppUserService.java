package com.application.template.service.appUser;

import com.application.template.entity.appUser.AppUser;
import com.application.template.entity.appUser.auth.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    JwtAuthResponseBody login(AuthBody authBody);

    CaptchaAuthDTO getCaptchaCode(CaptchaAuthAccessWay accessWay);

    AppUser register(RegisterBody registerBody);
}
