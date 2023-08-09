package com.example.penpal.web.letter.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterListDto {

    List<LetterDto> letters = new ArrayList<>();

    public static LetterListDto from(List<LetterDto> letters) {
        LetterListDto letterListDto = new LetterListDto();
        letterListDto.letters = letters;
        return letterListDto;
    }
}
