package com.application.template.exceptionHandle;

public class ErrorMessage {
    private int httpStatus;
    private String errorMsg;

    public ErrorMessage(int httpStatus, String errorMsg) {
        this.errorMsg = errorMsg;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
