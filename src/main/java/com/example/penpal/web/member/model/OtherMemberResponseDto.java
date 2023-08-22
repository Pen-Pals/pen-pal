package com.example.penpal.web.member.model;

import com.example.penpal.domain.favor.entity.Favor;
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
public class OtherMemberResponseDto {
    private Long id;
    private String nickname;
    private Gender gender;
    private LocalDate birthday;
    private Favor favors;
    private String country;

    public static OtherMemberResponseDto toDto(Member member) {
        return new OtherMemberResponseDto(
                member.getId(),
                member.getNickname(),
                member.getGender(),
                member.getBirthday(),
                member.getFavors(),
                member.getCountry()
        );
    }
}
