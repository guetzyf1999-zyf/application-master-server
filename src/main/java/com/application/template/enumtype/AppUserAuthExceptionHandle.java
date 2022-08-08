package com.application.template.enumtype;

import org.springframework.security.authentication.*;

import java.util.Arrays;
import java.util.Optional;

public enum AppUserAuthExceptionHandle {
    LOCK("用户已被锁定", 1, LockedException.class),
    CREDENTIALS_EXPIRED("用户密码已过期", 1, CredentialsExpiredException.class),
    DISABLE("该账户已被禁用", 1, DisabledException.class),
    BAD_CREDENTIALS("账号密码错误", 1, BadCredentialsException.class),
    ACCOUNT_EXPIRED("账户已过期", 5,AccountExpiredException .class);

    private final String message;
    private final int index;
    private final Class<? extends Exception> userAuthExceptionType;

    AppUserAuthExceptionHandle(String message, int index, Class<? extends Exception> userAuthExceptionType) {
        this.message = message;
        this.index = index;
        this.userAuthExceptionType = userAuthExceptionType;
    }
    
    public static String getHandleMessage(Class<? extends Exception> eClass) {
		Optional<AppUserAuthExceptionHandle> exceptionHandle = Arrays.stream(AppUserAuthExceptionHandle.values())
				.filter(handle -> handle.getUserAuthExceptionType().equals(eClass)).findFirst();
		return exceptionHandle.isPresent() ? exceptionHandle.get().getMessage() : "用户认证异常";
    }

    public String getMessage() {
        return message;
    }

    public int getIndex() {
        return index;
    }

    public Class<? extends Exception> getUserAuthExceptionType() {
        return userAuthExceptionType;
    }
}
