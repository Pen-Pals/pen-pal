package com.example.penpal.global.exception.model;

import com.example.penpal.global.exception.common.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessExceptionResponse {

    private final int status;
    private final String message;

    public static BusinessExceptionResponse from(BusinessException ex){
        return new BusinessExceptionResponse(ex.getStatus(), ex.getMessage());
    }

}
