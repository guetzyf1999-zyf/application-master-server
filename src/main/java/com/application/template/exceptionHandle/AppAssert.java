package com.application.template.exceptionHandle;

import com.application.template.exceptionHandle.exception.AppException;

public class AppAssert {
    private AppAssert() {
        throw new AppException("不允许构造");
    }

    public static void judge(boolean situation,RuntimeException exception){
        if(situation){
            throw exception;
        }
    }
}
