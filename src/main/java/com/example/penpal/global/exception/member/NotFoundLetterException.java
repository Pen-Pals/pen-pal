package com.example.penpal.global.exception.member;

import com.example.penpal.global.exception.code.ErrorCode;
import com.example.penpal.global.exception.common.BusinessException;

public class NotFoundLetterException extends BusinessException {

    public NotFoundLetterException() {
        super(ErrorCode.NOT_FOUND_LETTER_EXCEPTION);
    }
}
