package com.application.template.exceptionHandle;


import com.application.template.exceptionHandle.exception.AppException;
import com.application.template.exceptionHandle.exception.AppUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionProcess {

    @ExceptionHandler(AppUserException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage hasNotToken(AppUserException exception) {
        return new ErrorMessage(401, exception.getMessage());
    }

    @ExceptionHandler(AppException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage systemError(AppException exception) {
        return new ErrorMessage(500, exception.getMessage());
    }
}
