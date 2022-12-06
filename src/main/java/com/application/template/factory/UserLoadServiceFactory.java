package com.application.template.factory;

import com.application.template.enumtype.LoginAuthWay;
import com.application.template.service.authentication.userloadserivce.UserLoadService;
import com.application.template.util.SpringUtil;

import java.util.EnumMap;
import java.util.Map;

public class UserLoadServiceFactory {
    private static final Map<LoginAuthWay, UserLoadService> USER_LOAD_SERVICE_FACTORY = new EnumMap<>(LoginAuthWay.class);

    private UserLoadServiceFactory() {
        throw new UnsupportedOperationException("工厂禁止初始化");
    }

    public static UserLoadService getUserLoadService(LoginAuthWay authWay) {
        return SpringUtil.getBean(USER_LOAD_SERVICE_FACTORY.get(authWay).getClass());
    }

    public static void putMessageService(LoginAuthWay authWay,UserLoadService userLoadService) {
        USER_LOAD_SERVICE_FACTORY.put(authWay, userLoadService);
    }
}
