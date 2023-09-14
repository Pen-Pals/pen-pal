package com.example.penpal.global.exception.common;


import com.example.penpal.global.exception.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
