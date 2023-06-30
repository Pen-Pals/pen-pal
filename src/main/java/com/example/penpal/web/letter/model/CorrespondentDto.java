package com.example.penpal.web.letter.model;

import com.example.penpal.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorrespondentDto {

    int unreadCount;
    private Member member;

    public static CorrespondentDto of(int unreadCount, Member member){
        CorrespondentDto correspondentDto = new CorrespondentDto();
        correspondentDto.unreadCount = unreadCount;
        correspondentDto.member = member;
        return correspondentDto;
    }
}
