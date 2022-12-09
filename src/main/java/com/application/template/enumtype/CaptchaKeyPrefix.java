package com.application.template.enumtype;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.application.template.exceptionHandle.exception.AppException;

public enum CaptchaKeyPrefix {
	LOGIN_BY_PHONE("phone_login--", 10, 1, TimeUnit.MINUTES, MessageSendingApproach.SMS), LOGIN_BY_EMAIL(
			"email_login--", 20, 1, TimeUnit.MINUTES, MessageSendingApproach.EMAIL), REGISTER_BY_EMAIL("register--", 30,
					1, TimeUnit.MINUTES, MessageSendingApproach.EMAIL);

	private final String prefix;
	private final Integer index;
	private final long timeout;
	private final TimeUnit unit;
	private final MessageSendingApproach messageSendingApproach;

	CaptchaKeyPrefix(String prefix, Integer index, long timeout, TimeUnit unit, MessageSendingApproach approach) {
		this.index = index;
		this.prefix = prefix;
		this.timeout = timeout;
		this.unit = unit;
		this.messageSendingApproach = approach;
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
}
