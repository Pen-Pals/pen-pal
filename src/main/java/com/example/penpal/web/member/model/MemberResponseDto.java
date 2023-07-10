package com.example.penpal.web.member.model;

import com.example.penpal.domain.favor.entity.Favor;
import com.example.penpal.domain.member.entity.Gender;
import com.example.penpal.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private LocalDate birthday;
    private Favor favors;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getGender(),
                member.getBirthday(),
                member.getFavors()
        );
    }
}
