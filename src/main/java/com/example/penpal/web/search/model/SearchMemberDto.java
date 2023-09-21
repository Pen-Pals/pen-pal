package com.example.penpal.web.search.model;

import com.example.penpal.domain.member.entity.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class SearchMemberDto {

    private Member member;
    private boolean isFriend;

    public static SearchMemberDto from(Member member){
       return SearchMemberDto.builder()
               .member(member)
               .isFriend(false)
               .build();
    }
}
