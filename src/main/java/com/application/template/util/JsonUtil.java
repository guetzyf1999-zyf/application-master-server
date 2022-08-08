package com.application.template.util;

import com.application.template.exceptionHandle.exception.AppException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil(){
        throw new AppException("不允许构造");
    }

    public static String toJson(Object obj) {
        String result;
        try {
            result = MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new AppException("json转换失败");
        }
        return result;
    }

    public static <T> T toObject(String json,Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new AppException("json转换失败");
        }
    }
}
