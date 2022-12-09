package com.application.template.service.appUser;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.application.template.dto.login.RegisterBody;
import com.application.template.entity.appUser.AppUser;

public interface AppUserService extends UserDetailsService {

    AppUser register(RegisterBody registerBody);

    AppUser getUserByUsername(String username);

    AppUser getUserByTelephone(String phone);

    AppUser getUserByEmail(String email);

    String getEmailByUsername(String username);
}
