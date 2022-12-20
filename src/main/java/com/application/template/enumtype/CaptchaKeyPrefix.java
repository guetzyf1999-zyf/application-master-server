package com.application.template.enumtype;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.application.template.exceptionHandle.exception.AppException;

public enum CaptchaKeyPrefix {
	/*
	 * 手机号登录验证码前缀
	 */
	LOGIN_BY_PHONE("phone_login--", 10, 1, TimeUnit.MINUTES, MessageSendingApproach.SMS,
			"您本次的电话登录验证码有效时长为1分钟,请尽快验证,验证码为"),
	/*
	 * 邮箱登录验证码前缀
	 */
	LOGIN_BY_EMAIL("email_login--", 20, 1, TimeUnit.MINUTES, MessageSendingApproach.EMAIL,
			"您本次的邮箱登录验证码有效时长为1分钟,请尽快验证,验证码为"),
	/*
	 * 邮箱注册验证码前缀
	 */
	REGISTER_BY_EMAIL("register--", 30, 1, TimeUnit.MINUTES, MessageSendingApproach.EMAIL,
			"您本次的注册登录验证码有效时长为1分钟,请尽快验证,验证码为"),
	/*
	 * 邮箱注册验证码前缀
	 */
	RESET_PASSWORD_BY_PHONE("reset_password_by_phone--", 40, 10, TimeUnit.MINUTES, MessageSendingApproach.SMS,
			"您本次重置密码验证码有效时长为10分钟,请尽快验证,验证码为"),
	/*
	 * 邮箱注册验证码前缀
	 */
	RESET_PASSWORD_BY_EMAIL("reset_password_by_email--", 50, 10, TimeUnit.MINUTES, MessageSendingApproach.EMAIL,
			"您本次重置密码验证码有效时长为10分钟,请尽快验证,验证码为");

	private final String prefix;
	private final Integer index;
	private final long timeout;
	private final TimeUnit unit;
	private final MessageSendingApproach messageSendingApproach;
	private final String describe;

	CaptchaKeyPrefix(String prefix, Integer index, long timeout, TimeUnit unit, MessageSendingApproach approach,
			String describe) {
		this.index = index;
		this.prefix = prefix;
		this.timeout = timeout;
		this.unit = unit;
		this.messageSendingApproach = approach;
		this.describe = describe;
	}

	public static CaptchaKeyPrefix getCaptchaKeyPrefixByIndex(Integer index) {
		return Arrays.stream(CaptchaKeyPrefix.values()).filter(prefix -> prefix.index.equals(index)).findFirst()
				.orElseThrow(() -> new AppException("找不到对应的验证码类型"));
	}

	public String getPrefix() {
		return prefix;
	}

	public Integer getIndex() {
		return index;
	}

	public long getTimeout() {
		return timeout;
	}

	public TimeUnit getUnit() {
		return unit;
	}

	public MessageSendingApproach getMessageSendingApproach() {
		return messageSendingApproach;
	}

	public String getDescribe() {
		return describe;
	}
}
