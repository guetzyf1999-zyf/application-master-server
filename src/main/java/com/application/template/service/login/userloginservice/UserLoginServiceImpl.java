package com.application.template.service.login.userloginservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.entity.appUser.AppUser;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.service.appUser.AppUserService;
import com.application.template.service.authentication.UserAuthenticationService;
import com.application.template.service.authentication.jwtservice.JwtService;

@Service
@Transactional
public class UserLoginServiceImpl {

    protected static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    protected JwtService jwtService;

    @Autowired
    protected UserAuthenticationService authenticationService;

    @Autowired
    protected AppUserService appUserService;

	public JwtAuthResponseBody loginByCaptchaCode(LoginAuthBody loginAuthBody, LoginAuthWay loginAuthWay,
			AppUser user) {
		String captchaKey = loginAuthWay.getPrefix().getPrefix() + loginAuthBody.getVerifyId();
		authenticationService.checkCaptchaCode(captchaKey, loginAuthBody.getVerifyCode());
		UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(user,
				user.getPassword(), user.getAuthorities());
		String jwtToken = signJwtTokenAndSetSecurityContext(user, authenticate);
		return new JwtAuthResponseBody(user, jwtToken);
	}

    public String signJwtTokenAndSetSecurityContext(AppUser user, UsernamePasswordAuthenticationToken authenticate) {
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtService.sing(user.getUsername(), user.getTelephone());
    }
}
