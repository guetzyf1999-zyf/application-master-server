package com.application.template.controller;

import com.application.template.config.applicationProps.MessageConfigProps;
import com.application.template.constant.ExternalServiceAddress;
import com.application.template.entity.appUser.AppUser;
import com.application.template.entity.appUser.auth.RegisterBody;
import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.mapper.appUser.AppUserMapper;
import com.application.template.service.factory.MessageSendingServiceFactory;
import com.application.template.service.message.MessageService;
import com.application.template.util.HttpUtil;
import com.application.template.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("app/test/")
public class TestController {

    @Autowired
    private AppUserMapper appUserMapper;

    @Value("${jwtauth.unauthpath}")
    private String unAuthPath;

    @GetMapping("my-test")
    public void test() {
        String[] split = unAuthPath.split(",");
        System.out.println(Arrays.toString(split));
    }


    @GetMapping("json-test")
    public String jsonTest() {
        AppUser userById = appUserMapper.findUserById(1);
        String s = HttpUtil.httpPost(ExternalServiceAddress.TEST + "json-test", userById);
        Map<String, Object> map = new HashMap<>();
        map.put("name","156");
        HttpUtil.httpGetWithParams(ExternalServiceAddress.TEST + "my-test", map);
        return s;
    }

    @GetMapping("redis-test")
    public void redisTest() {
        HttpUtil.httpPost(ExternalServiceAddress.TEST + "redis-test", null);
    }
}
