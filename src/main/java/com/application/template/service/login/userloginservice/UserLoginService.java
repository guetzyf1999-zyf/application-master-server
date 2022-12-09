package com.application.template.service.login.userloginservice;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.enumtype.LoginAuthWay;

public interface UserLoginService {

    JwtAuthResponseBody login(LoginAuthBody loginAuthBody, LoginAuthWay loginAuthWay);
}
