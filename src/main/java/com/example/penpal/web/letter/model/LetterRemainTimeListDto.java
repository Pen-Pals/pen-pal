package com.example.penpal.web.letter.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterRemainTimeListDto {

    List<LetterRemainTimeDto> letters = new ArrayList<>();

    public static LetterRemainTimeListDto from(List<LetterRemainTimeDto> letters) {
        LetterRemainTimeListDto letterListDto = new LetterRemainTimeListDto();
        letterListDto.letters = letters;
        return letterListDto;
    }
}
