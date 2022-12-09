package com.application.template.service.login;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;

public interface LoginService {

    JwtAuthResponseBody login(LoginAuthBody loginAuthBody);
}
