package com.application.template.controller;

import com.application.template.constant.ExternalServiceAddress;
import com.application.template.entity.appUser.AppUser;
import com.application.template.mapper.appUser.AppUserMapper;
import com.application.template.util.HttpUtil;
import com.application.template.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
        Map<String, String> params = new HashMap<>();
        params.put("username", "%6%");
        params.put("nickName", "%石乐志%");
        List<AppUser> appUserByParams = appUserMapper.findAppUserByParams(params);
        System.out.println(JsonUtil.toJson(appUserByParams));
    }


    @GetMapping("json-test")
    public String jsonTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("ip","192.168.1.92");
        map.put("type","1");
        String s = HttpUtil.httpGetWithParams(ExternalServiceAddress.IP_ADD, map);
        return s;
    }

    @GetMapping("redis-test")
    public void redisTest() {
        HttpUtil.httpPost(ExternalServiceAddress.TEST + "redis-test", null);
    }
}
