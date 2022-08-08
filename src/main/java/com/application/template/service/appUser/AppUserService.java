package com.application.template.service.appUser;

import com.application.template.entity.appUser.AppUser;
import com.application.template.entity.appUser.auth.AuthBody;
import com.application.template.entity.appUser.auth.CaptchaAuthDTO;
import com.application.template.entity.appUser.auth.RegisterBody;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    void login(AuthBody authBody);

    CaptchaAuthDTO getCaptchaCode(RegisterBody registerBody);

    AppUser register(RegisterBody registerBody);
}
