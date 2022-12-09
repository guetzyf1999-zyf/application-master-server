package com.application.template.service.login.userloginservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.entity.appUser.AppUser;
import com.application.template.enumtype.AppUserAuthExceptionHandle;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.exceptionHandle.exception.AppUserException;
import com.application.template.factory.UserLoginServiceFactory;
import com.application.template.service.appUser.AppUserService;
import com.application.template.util.SpringUtil;

@Service
@Transactional
public class UserLoginByVerifyPasswordServiceImpl extends UserLoginServiceImpl
		implements UserLoginService, InitializingBean {

	@Autowired
	private AppUserService appUserService;

	@Override
	public JwtAuthResponseBody login(LoginAuthBody loginAuthBody, LoginAuthWay loginAuthWay) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					loginAuthBody.getVerifyId(), loginAuthBody.getVerifyCode());
			Authentication authenticate = SpringUtil.getBean(AuthenticationManager.class)
					.authenticate(authenticationToken);
			AppUser user = (AppUser) authenticate.getPrincipal();
			user.setEmail(appUserService.getEmailByUsername(user.getUsername()));
			String jwtToken = super.signJwtTokenAndSetSecurityContext(user, authenticationToken);
			return new JwtAuthResponseBody(user, jwtToken);
		} catch (Exception exception) {
			String handleMessage = handleAuthUserException(exception);
			throw new AppUserException(handleMessage);
		}
	}

	@Override
	public void afterPropertiesSet() {
		UserLoginServiceFactory.putUserLoginService(LoginAuthWay.PASSWORD, this);
	}

	private String handleAuthUserException(Exception exception) {
		exception.printStackTrace();
		String handleMessage = AppUserAuthExceptionHandle.getHandleMessage(exception.getClass());
		logger.info(handleMessage);
		return handleMessage;
	}
}
