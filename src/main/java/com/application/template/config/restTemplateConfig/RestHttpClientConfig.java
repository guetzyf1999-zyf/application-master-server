package com.application.template.config.restTemplateConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestHttpClientConfig {

    @Bean
    public RestTemplate genRestTemplate(ClientHttpRequestFactory httpRequestFactory){
        RestTemplate template = new RestTemplate(httpRequestFactory);
        template.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return template;
    }

    @Bean
    public ClientHttpRequestFactory simpleHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(50000);
        return factory;
    }
}
