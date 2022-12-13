package com.application.template.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.template.service.authentication.UserAuthenticationService;

@RestController
@RequestMapping("app/authentication/")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationService authenticationService;

	@GetMapping("get-captcha-code")
	public void getCaptchaCode(@RequestParam String title, @RequestParam String receivingId,
			@RequestParam Integer captchaKeyPrefixIndex) {
		authenticationService.getCaptchaCode(title, captchaKeyPrefixIndex, receivingId);
	}
}
