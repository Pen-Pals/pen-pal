package com.example.penpal.global.exception.common;

import com.example.penpal.global.exception.model.BusinessExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<BusinessExceptionResponse> handleBusinessException(BusinessException ex){
        BusinessExceptionResponse response = BusinessExceptionResponse.from(ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}
