package com.application.template.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    @Qualifier("objRedisTemplate")
    private RedisTemplate<String,Object> objRedisTemplate;
    @Autowired
    @Qualifier("strRedisTemplate")
    private RedisTemplate<String,String> strRedisTemplate;

    public void insert(String key,Object value) {
        objRedisTemplate.opsForValue().set(key, value);
    }

    public void insert(String key,Object value, long timout, TimeUnit unit) {
        objRedisTemplate.opsForValue().setIfAbsent(key, value, timout, unit);
    }

    public void insertStr(String key,String value) {
        strRedisTemplate.opsForValue().set(key, value);
    }

    public void insertStr(String key,String value, long timout, TimeUnit unit) {
        strRedisTemplate.opsForValue().setIfAbsent(key, value, timout, unit);
    }

    public Object get(String key) {
        return objRedisTemplate.opsForValue().get(key);
    }

    public String getStr(String key) {
        return strRedisTemplate.opsForValue().get(key);
    }
}
