package com.application.template.service.message.smsMessageService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.config.applicationProps.SmsMessageConfigProps;
import com.application.template.constant.ExternalServiceAddress;
import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.factory.MessageSendingServiceFactory;
import com.application.template.service.message.MessageService;
import com.application.template.util.HttpUtil;

@Service("smsMessageServiceImpl")
public class SmsMessageServiceImpl implements MessageService, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SmsMessageServiceImpl.class);

    @Autowired
    private SmsMessageConfigProps smsMessageConfigProps;

    @Override
    @TimeCount(name = "sendSMSCaptchaMessage")
    public void sendCaptchaMessage(String title, String phone, String smsText, String captcha) {
        AppAssert.judge(!StringUtils.hasText(phone),new AppException("请先填写电话!"));
        Map<String, Object> params = new HashMap<>();
        params.put("Key", smsMessageConfigProps.getKey());
        params.put("Uid", smsMessageConfigProps.getUid());
        params.put("smsMob", phone);
        params.put("smsText", title + ":" + smsText + captcha);
        String response = HttpUtil.httpGetWithParams(ExternalServiceAddress.SMS_MESSAGE_HOST, params);
        int responseCode = Integer.parseInt(response);
        logger.error(SmsExceptionType.getSmsExceptionTypeByIndex(responseCode).getMessage());
		AppAssert.judge(0 > responseCode, new AppException("短信服务出错"));
    }

    @Override
    public void afterPropertiesSet() {
        MessageSendingServiceFactory.putMessageService(MessageSendingApproach.SMS, this);
    }

    private enum SmsExceptionType {
        /*
         * 没有该用户账户
         * */
        NO_SUCH_USER("没有该用户账户", -1),
        /*
         * 接口密钥不正确,不是账户登陆密码
         * */
        INCORRECT_KEY("接口密钥不正确,不是账户登陆密码", -2),
        /*
         * MD5接口密钥加密不正确
         * */
        INCORRECT_MD5_KEY("MD5接口密钥加密不正确", -21),
        /*
         * 短信数量不足
         * */
        SMS_OUT_OF_NUMBER("短信数量不足", -3),
        /*
         * 该用户被禁用
         * */
        USER_DISABLE("该用户被禁用", -11),
        /*
         * 短信内容出现非法字符
         * */
        SMS_TEXT_ILLEGAL("短信内容出现非法字符", -14),
        /*
         * 手机号格式不正确
         * */
        PHONE_FORMAT_WRONG("手机号格式不正确", -4),
        /*
         * 手机号码为空
         * */
        EMPTY_PHONE("手机号码为空", -41),
        /*
         * 短信内容为空
         * */
        EMPTY_SMS_TEXT("短信内容为空", -42),
        /*
         * 短信签名格式不正确
         * */
        SMS_SIGNATURE_FORMAT_WRONG("短信签名格式不正确", -51),
        /*
         * 短信签名太长建议签名10个字符以内
         * */
        SMS_SIGNATURE_EXCEEDING_LENGTH("短信签名太长建议签名10个字符以内", -52),
        /*
         * IP限制
         * */
        IP_RESTRICTED("IP限制", -6);

        private final String message;
        private final Integer index;

        SmsExceptionType(String message, Integer index) {
            this.message = message;
            this.index = index;
        }

        private static SmsExceptionType getSmsExceptionTypeByIndex(Integer index) {
			return Arrays.stream(SmsExceptionType.values())
					.filter(type -> index.equals(type.index)).findFirst()
					.orElseThrow(() -> new AppException("未找到对应短信错误类型"));
		}

        private String getMessage() {
            return message;
        }

        private Integer getIndex() {
            return index;
        }
    }
}
