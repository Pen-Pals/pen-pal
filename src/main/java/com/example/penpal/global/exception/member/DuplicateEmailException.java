package com.example.penpal.global.exception.member;

import com.example.penpal.global.exception.code.ErrorCode;
import com.example.penpal.global.exception.common.BusinessException;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL_EXCEPTION);
    }
}
