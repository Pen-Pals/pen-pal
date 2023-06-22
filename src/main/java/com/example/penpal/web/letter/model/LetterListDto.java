package com.example.penpal.web.letter.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LetterListDto {

    private int totalPage;
    List<PageLetterDto> letters;

    public static LetterListDto of(int totalPage, List<PageLetterDto> letters){
        return LetterListDto.builder()
                .totalPage(totalPage)
                .letters(letters)
                .build();
    }
}
