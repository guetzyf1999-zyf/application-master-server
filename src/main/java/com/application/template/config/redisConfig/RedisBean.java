package com.application.template.config.redisConfig;

import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisBean {


    @Primary
    @Bean(name = "objRedisTemplate")
    public RedisTemplate<String,Object> genRedisTemplate(LettuceConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean(name = "strRedisTemplate")
    public RedisTemplate<String, String> getStringStringRedisTemplate(LettuceConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.string());
        return template;
    }

    @Bean
    public LettuceConnectionFactory genLettuceConnectionFactory(RedisStandaloneConfiguration standaloneConfiguration,
              LettuceClientConfiguration clientConfiguration) {
        return new LettuceConnectionFactory(standaloneConfiguration,clientConfiguration);
    }

    @Bean
    public RedisStandaloneConfiguration genRedisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
        return redisStandaloneConfiguration;
    }

    @Bean
    public LettuceClientConfiguration genLettuceClientConfiguration() {
        GenericObjectPoolConfig<String> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxIdle(100);
        genericObjectPoolConfig.setMinIdle(0);
        genericObjectPoolConfig.setMaxTotal(20);
        genericObjectPoolConfig.setMaxWait(BaseObjectPoolConfig.DEFAULT_MAX_WAIT);
        return LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).build();
    }
}
