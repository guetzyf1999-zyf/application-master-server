package com.application.template.util;

import com.application.template.exceptionHandle.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {
        throw new AppException("不允许构造");
    }

    public static String httpGet(String url) {
        return httpRequest(url,null,getInitHeader(),HttpMethod.GET);
    }

    public static String httpGetWithParamsAndHeaders(String url, Map<String,Object> params,
             Map<String,String> headerParams) {
        String completeUrl = assemblyParameters(url, params);
        HttpHeaders headers = getInitHeader();
        headerParams.forEach(headers::add);
        return httpRequest(completeUrl,null,headers,HttpMethod.GET);
    }

    public static String httpGetWithParams(String url, Map<String,Object> params) {
        String completeUrl = assemblyParameters(url, params);
        HttpHeaders headers = getInitHeader();
        return httpRequest(completeUrl,null,headers,HttpMethod.GET);
    }

    public static String httpGetWithHeader(String url, Map<String,String> headerParams) {
        HttpHeaders headers = getInitHeader();
        headerParams.forEach(headers::add);
        return httpRequest(url,null,headers,HttpMethod.GET);
    }

    public static String httpPost(String url,Object requestBody) {
        return httpRequest(url, requestBody, getInitHeader(),HttpMethod.POST);
    }

    public static String httpPostWithParams(String url,Object requestBody, Map<String,Object> params) {
        String completeUrl = assemblyParameters(url, params);
        return httpRequest(completeUrl, requestBody, getInitHeader(),HttpMethod.POST);
    }

    public static String httpPostWithHeader(String url,Object requestBody, Map<String,String> headerParams) {
        HttpHeaders headers = getInitHeader();
        headerParams.forEach(headers::add);
        return httpRequest(url, requestBody, headers,HttpMethod.POST);
    }

    public static String httpPostWithParamsAndHeaders(String url, Object requestBody, Map<String,Object> params,
                Map<String,String> headerParams) {
        String completeUrl = assemblyParameters(url, params);
        HttpHeaders headers = getInitHeader();
        headerParams.forEach(headers::add);
        return httpRequest(completeUrl,requestBody,headers,HttpMethod.POST);
    }

    private static String httpRequest(String url,Object requestBody,HttpHeaders headers, HttpMethod httpMethod) {
        String requestJson = JsonUtil.toJson(requestBody);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        RestTemplate template = SpringUtil.getBean(RestTemplate.class);
        ParameterizedTypeReference<String> responseType = ParameterizedTypeReference.forType(String.class);
        ResponseEntity<String> response = template.exchange(url, httpMethod, requestEntity, responseType);
        String result = response.getBody();
        logInfo(requestJson, url, result);
        return result;
    }

    private static String assemblyParameters(String url, Map<String,Object> params) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        params.forEach(assemblyUrl(builder));
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static BiConsumer<String,Object> assemblyUrl(StringBuilder builder) {
        return (k, v) -> builder.append(k).append("=").append(v != null ? v.toString() : "").append("&");
    }

    private static HttpHeaders getInitHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private static void logInfo(String requestJson, String url, String result) {
        boolean hasBody = !"null".equals(requestJson);
        String title = hasBody ? "请求体为{},请求返回结果为:{},请求url为:{}" : "请求返回结果为:{},请求url为:{}";
        Object[] info = hasBody ? new Object[]{requestJson, result, url} : new Object[]{result, url};
        logger.info(title, info);
    }
}
