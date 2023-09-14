package com.example.penpal.web.profile;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.profile.entity.Profile;
import com.example.penpal.web.member.model.MemberResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileResponseDto {
    String profileUrl;
    String profileText;

    public static ProfileResponseDto toDto(Profile profile) {
        return new ProfileResponseDto(
                profile.getProfileUrl(),
                profile.getProfileText()
        );
    }


}
