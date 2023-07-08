package com.example.penpal.web.member.model;

import com.example.penpal.domain.member.entity.Gender;
import com.example.penpal.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberInfoDto {

    private Long id;
    private String email;
    private String nickname;
    private Gender gender;
    private LocalDate birthday;

    public static MemberInfoDto toDto(Member member) {
        return new MemberInfoDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getGender(),
                member.getBirthday()
        );
    }
}
