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

    private String content;

    public static Letter toEntity(SendLetterRequest request, Member member, Long receiveId) {
        return Letter.builder()
                .member(member)
                .sendId(member.getId())
                .receiveId(receiveId)
                .content(request.content)
                .sendDate(LocalDateTime.now())
                .receiveDate(LocalDateTime.now())
                .deliveryTime(LocalDateTime.now().plusHours(24))
                .build();
    }
}
