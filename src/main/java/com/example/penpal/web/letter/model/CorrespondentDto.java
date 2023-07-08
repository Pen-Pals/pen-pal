package com.example.penpal.web.letter.model;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.web.member.model.MemberInfoDto;
import com.example.penpal.web.member.model.MemberResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorrespondentDto {

    int unreadCount;
    private MemberInfoDto member;

    public static CorrespondentDto of(int unreadCount, Member member) {
        CorrespondentDto correspondentDto = new CorrespondentDto();
        correspondentDto.unreadCount = unreadCount;
        correspondentDto.member = MemberInfoDto.toDto(member);
        return correspondentDto;
    }
}
