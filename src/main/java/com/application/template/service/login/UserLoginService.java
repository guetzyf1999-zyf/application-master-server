package com.application.template.service.login;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;

public interface UserLoginService {

    JwtAuthResponseBody loginByPassWord(LoginAuthBody loginAuthBody);

    JwtAuthResponseBody loginByCaptchaCode(LoginAuthBody loginAuthBody);
}
