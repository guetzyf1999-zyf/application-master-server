package com.application.template.controller.authentication;

import com.application.template.dto.auth.CaptchaAuthAccessWay;
import com.application.template.dto.auth.CaptchaAuthDTO;
import com.application.template.service.authentication.UserAuthenticationService;
import com.application.template.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/authentication/")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationService authenticationService;

    @GetMapping("get-captcha-code")
    public CaptchaAuthDTO getCaptchaCode(@RequestParam String accessWay) {
        return authenticationService.getCaptchaCode(JsonUtil.toObject(accessWay, CaptchaAuthAccessWay.class));
    }
}
