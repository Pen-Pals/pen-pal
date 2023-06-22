package com.example.penpal.web.letter.model;

import com.example.penpal.domain.letter.entity.Letter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PageLetterDto {

    private String nickname;
    private Long sendId;
    private Long receiveId;
    private LocalDateTime sendDate;
    private String content;

    public static PageLetterDto from(Letter letter){
        return PageLetterDto.builder()
                .nickname(letter.getMember().getNickname())
                .sendId(letter.getSendId())
                .receiveId(letter.getReceiveId())
                .sendDate(letter.getSendDate())
                .content(letter.getContent())
                .build();
    }

}
