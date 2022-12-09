package com.application.template.service.authentication.userloadserivce;

import org.springframework.security.core.userdetails.UserDetails;

import com.application.template.dto.auth.LoginAuthBody;

public interface UserLoadService {
    UserDetails loadUserByUsername(LoginAuthBody loginAuthBody);
}
