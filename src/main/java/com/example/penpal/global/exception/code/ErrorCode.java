package com.example.penpal.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_MEMBER_EXCEPTION(404, "사용자가 존재하지 않습니다.", NOT_FOUND),
    NOT_FOUND_LETTER_EXCEPTION(404, "편지가 존재하지 않습니다.", NOT_FOUND);

    private final int status;
    private final String message;
    private final HttpStatus httpStatus;

}
