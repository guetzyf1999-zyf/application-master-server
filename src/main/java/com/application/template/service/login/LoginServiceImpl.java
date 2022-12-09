package com.application.template.service.login;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.factory.UserLoginServiceFactory;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Override
	public JwtAuthResponseBody login(LoginAuthBody loginAuthBody) {
		LoginAuthWay authWay = LoginAuthWay.getLoginAuthWayByIndex(loginAuthBody.getLoginAuthWay());
		return UserLoginServiceFactory.getUserLoginService(authWay).login(loginAuthBody, authWay);
	}
}
