package com.example.penpal.global.exception.member;

import com.example.penpal.global.exception.code.ErrorCode;
import com.example.penpal.global.exception.common.BusinessException;

public class NotFoundMemberException extends BusinessException {

    public NotFoundMemberException() {
        super(ErrorCode.NOT_FOUND_MEMBER_EXCEPTION);

    }
}
