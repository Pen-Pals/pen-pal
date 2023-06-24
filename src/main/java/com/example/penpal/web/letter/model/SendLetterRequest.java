package com.example.penpal.web.letter.model;

import com.example.penpal.domain.letter.entity.Letter;
import com.example.penpal.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendLetterRequest {

    private Long receiveId;
    private String content;
    private LocalDateTime sendDate;
    private LocalDateTime receiveDate;
    private LocalDateTime deliveryTime;

    public static Letter toEntity(SendLetterRequest request, Member member){
        return Letter.builder()
                .member(member)
                .sendId(member.getId())
                .receiveId(request.receiveId)
                .sendDate(request.sendDate)
                .receiveDate(request.receiveDate)
                .deliveryTime(request.deliveryTime)
                .content(request.content)
                .build();
    }
}
