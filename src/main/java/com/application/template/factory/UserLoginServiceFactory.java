package com.application.template.factory;

import java.util.EnumMap;
import java.util.Map;

import com.application.template.enumtype.LoginAuthWay;
import com.application.template.service.login.userloginservice.UserLoginService;
import com.application.template.util.SpringUtil;

public class UserLoginServiceFactory {
	private static final Map<LoginAuthWay, UserLoginService> USER_LOAD_SERVICE_FACTORY = new EnumMap<>(
			LoginAuthWay.class);

	private UserLoginServiceFactory() {
		throw new UnsupportedOperationException("工厂禁止初始化");
	}

	public static UserLoginService getUserLoginService(LoginAuthWay authWay) {
		return SpringUtil.getBean(USER_LOAD_SERVICE_FACTORY.get(authWay).getClass());
	}

	public static void putUserLoginService(LoginAuthWay authWay, UserLoginService userLoginService) {
		USER_LOAD_SERVICE_FACTORY.put(authWay, userLoginService);
	}
}
