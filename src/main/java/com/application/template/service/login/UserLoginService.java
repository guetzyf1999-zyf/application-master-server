package com.application.template.service.login;

import com.application.template.dto.auth.AuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;

public interface UserLoginService {

    JwtAuthResponseBody loginByPassWord(AuthBody authBody);
}
