package com.example.penpal.global.exception.member;

import com.example.penpal.global.exception.code.ErrorCode;
import com.example.penpal.global.exception.common.BusinessException;

public class NotExistLocationInfoException extends BusinessException {

    public NotExistLocationInfoException() {
        super(ErrorCode.NOT_EXIST_LOCATION_INFO_EXCEPTION);

    }
}
