package com.example.penpal.web.letter.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterListDto {

    private int totalCount;
    List<PageLetterDto> letters;

    @Builder
    public static LetterListDto of(int totalCount, List<PageLetterDto> letters){
        return LetterListDto.builder()
                .totalCount(totalCount)
                .letters(letters)
                .build();
    }
}
