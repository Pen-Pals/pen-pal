package com.example.penpal.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_MEMBER_EXCEPTION(404, "사용자가 존재하지 않습니다.", NOT_FOUND),
    NOT_FOUND_LETTER_EXCEPTION(404, "편지가 존재하지 않습니다.", NOT_FOUND),
    DUPLICATE_EMAIL_EXCEPTION(409, "이미 사용중인 이메일입니다", CONFLICT),
    WRONG_TIME_FORMAT_EXCEPTION(400, "잘못된 시간 형식입니다.", BAD_REQUEST),
    NOT_EXIST_LOCATION_INFO_EXCEPTION(404, "위치 정보가 존재하지 않습니다.", NOT_FOUND);

    private final int status;
    private final String message;
    private final HttpStatus httpStatus;

}
