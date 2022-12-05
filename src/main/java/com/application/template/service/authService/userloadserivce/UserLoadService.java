package com.application.template.service.authService.userloadserivce;

import com.application.template.dto.auth.AuthBody;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserLoadService {
    UserDetails loadUserByUsername(AuthBody authBody);
}
