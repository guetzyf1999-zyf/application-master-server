package com.application.template.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.service.login.UserLoginService;

@RestController
@RequestMapping("app/login/")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("login")
    public JwtAuthResponseBody login(@RequestBody LoginAuthBody loginAuthBody) {
        return userLoginService.loginByPassWord(loginAuthBody);
    }

    @PostMapping("login-by-captcha-code")
    public JwtAuthResponseBody loginByCaptchaCode(@RequestBody LoginAuthBody loginAuthBody) {
        return userLoginService.loginByCaptchaCode(loginAuthBody);
    }
}
