package com.example.penpal.web.letter.model;

import com.example.penpal.domain.letter.entity.Letter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PageLetterDto {

    private Member member;
    private Long sendId;
    private Long receiveId;
    private LocalDateTime sendDate;
    private String content;

    public static PageLetterDto from(Letter letter){
        return PageLetterDto.builder()
                .member(letter.getMember())
                .sendId(letter.getSendId())
                .receiveId(letter.getReceiveId())
                .sendDate(letter.getSendDate())
                .content(letter.getContent())
                .build();
    }

}
