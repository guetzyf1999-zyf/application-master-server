package com.application.template.controller.appUser;

import com.application.template.entity.appUser.AppUser;
import com.application.template.entity.appUser.auth.*;
import com.application.template.service.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/user/")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;


    @PostMapping("login")
    public JwtAuthResponseBody login(@RequestBody AuthBody authBody) {
        return appUserService.login(authBody);
    }

    @PostMapping("get-captcha-code")
    public CaptchaAuthDTO getCaptchaCode(@RequestBody CaptchaAuthAccessWay accessWay) {
        return appUserService.getCaptchaCode(accessWay);
    }

    @PostMapping("register")
    public AppUser register(@RequestBody RegisterBody registerBody) {
        return appUserService.register(registerBody);
    }
}
