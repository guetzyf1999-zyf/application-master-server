package com.application.template.controller.login;

import com.application.template.dto.auth.AuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.service.login.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/login/")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("login-by-pass-word")
    public JwtAuthResponseBody loginByPassWord(@RequestBody AuthBody authBody) {
        return userLoginService.loginByPassWord(authBody);
    }
}
