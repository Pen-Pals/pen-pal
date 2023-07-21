package com.example.penpal.global.exception.letter;

import com.example.penpal.global.exception.code.ErrorCode;
import com.example.penpal.global.exception.common.BusinessException;

public class WrongTimeFormatException extends BusinessException {

    public WrongTimeFormatException() {
        super(ErrorCode.WRONG_TIME_FORMAT_EXCEPTION);
    }
}
