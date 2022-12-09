package com.application.template.service.login.userloginservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.factory.UserLoginServiceFactory;

@Service
@Transactional
public class UserLoginByEmailServiceImpl extends UserLoginServiceImpl implements UserLoginService, InitializingBean {

    @Override
	public JwtAuthResponseBody login(LoginAuthBody loginAuthBody, LoginAuthWay loginAuthWay) {
		return super.loginByCaptchaCode(loginAuthBody, loginAuthWay);
	}

    @Override
	public void afterPropertiesSet() {
		UserLoginServiceFactory.putUserLoginService(LoginAuthWay.EMAIL, this);
	}
}
