package com.application.template.controller.appUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.application.template.dto.auth.ResetPasswordParamsDTO;
import com.application.template.dto.login.RegisterBody;
import com.application.template.entity.appUser.AppUser;
import com.application.template.service.appUser.AppUserService;

@RestController
@RequestMapping("app/user/")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("register")
    public AppUser register(@RequestBody RegisterBody registerBody, @RequestParam String captchaKey) {
        return appUserService.register(registerBody, captchaKey);
    }

	@PutMapping("reset-password")
	public void resetPassword(@RequestBody ResetPasswordParamsDTO paramsDTO) {
        appUserService.resetPassword(paramsDTO);
	}
}
