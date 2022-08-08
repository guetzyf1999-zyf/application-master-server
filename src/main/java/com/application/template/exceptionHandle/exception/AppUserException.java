package com.application.template.exceptionHandle.exception;

public class AppUserException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AppUserException(String msg) {
        super(msg);
    }
}
