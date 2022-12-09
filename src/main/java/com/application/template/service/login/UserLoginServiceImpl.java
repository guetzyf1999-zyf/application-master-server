package com.application.template.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.entity.appUser.AppUser;
import com.application.template.enumtype.AppUserAuthExceptionHandle;
import com.application.template.enumtype.CaptchaKeyPrefix;
import com.application.template.exceptionHandle.exception.AppUserException;
import com.application.template.service.appUser.AppUserService;
import com.application.template.service.authentication.UserAuthenticationService;
import com.application.template.service.authentication.jwtservice.JwtService;
import com.application.template.util.JsonUtil;
import com.application.template.util.SpringUtil;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserAuthenticationService authenticationService;

    @Override
    public JwtAuthResponseBody loginByPassWord(LoginAuthBody loginAuthBody) {
        try {
            String authJson = JsonUtil.toJson(loginAuthBody);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authJson,
                    loginAuthBody.getPassword());
            Authentication authenticate = SpringUtil.getBean(AuthenticationManager.class).authenticate(authenticationToken);
            AppUser user = (AppUser) authenticate.getPrincipal();
            user.setEmail(appUserService.getEmailByUsername(user.getUsername()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String jwtToken = jwtService.sing(user.getUsername(), user.getTelephone());
            return new JwtAuthResponseBody(user, jwtToken);
        } catch (Exception exception) {
            String handleMessage = handleAuthUserException(exception);
            throw new AppUserException(handleMessage);
        }
    }

    @Override
    public JwtAuthResponseBody loginByCaptchaCode(LoginAuthBody loginAuthBody) {
		authenticationService.checkCaptchaCode(
				CaptchaKeyPrefix.LOGIN_BY_EMAIL.getPrefix() + loginAuthBody.getVerifyCredentials(),
				loginAuthBody.getCaptchaCode());
        AppUser user = (AppUser) authenticationService.loadUserByUsername(JsonUtil.toJson(loginAuthBody));
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken
                (user, user.getPassword(), user.getAuthorities());
        String jwtToken = jwtService.sing(user.getUsername(), user.getTelephone());
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new JwtAuthResponseBody(user, jwtToken);
    }

    private String handleAuthUserException(Exception exception) {
        exception.printStackTrace();
        String handleMessage = AppUserAuthExceptionHandle.getHandleMessage(exception.getClass());
        logger.info(handleMessage);
        return handleMessage;
    }
}
