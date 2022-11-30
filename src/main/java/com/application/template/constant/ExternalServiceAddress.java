package com.application.template.constant;

import com.application.template.exceptionHandle.exception.AppException;

public class ExternalServiceAddress {
    private ExternalServiceAddress() {
        throw new AppException("不允许构造");
    }

    //测试服务接口
    public static final String TEST = "http://localhost:8752/api/test/";

    //SMS中国网建短信服务接口
    public static final String SMS_MESSAGE_HOST = "http://utf8.api.smschinese.cn/";

    public static final String IP_ADD = "http://ip.cn/api/index";
}
