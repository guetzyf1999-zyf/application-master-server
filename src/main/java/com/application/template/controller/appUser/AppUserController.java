package com.application.template.controller.appUser;

import com.application.template.entity.appUser.AppUser;
import com.application.template.entity.appUser.auth.AuthBody;
import com.application.template.entity.appUser.auth.CaptchaAuthDTO;
import com.application.template.entity.appUser.auth.RegisterBody;
import com.application.template.service.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/user/")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;


    @PostMapping("login")
    public void login(@RequestBody AuthBody authBody) {
        appUserService.login(authBody);
    }

    @PostMapping("get-captcha-code")
    public CaptchaAuthDTO getCaptchaCode(@RequestBody RegisterBody registerBody) {
        return appUserService.getCaptchaCode(registerBody);
    }

    @PostMapping("register")
    public AppUser register(@RequestBody RegisterBody registerBody) {
        return appUserService.register(registerBody);
    }
}
