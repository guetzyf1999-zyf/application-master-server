package com.application.template.service.appUser;

import com.application.template.dto.login.RegisterBody;
import com.application.template.entity.appUser.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserService {

    AppUser register(RegisterBody registerBody);

    UserDetails getUserByUsername(String username);

    UserDetails getUserByTelephone(String phone);

    UserDetails getUserByEmail(String email);

    String getEmailByUsername(String username);
}
