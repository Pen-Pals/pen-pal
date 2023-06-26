package com.example.penpal.web.letter.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PageLetterListDto {

    private int totalPage;
    List<LetterDto> letters;

    public static PageLetterListDto of(int totalPage, List<LetterDto> letters){
        return PageLetterListDto.builder()
                .totalPage(totalPage)
                .letters(letters)
                .build();
    }
}
