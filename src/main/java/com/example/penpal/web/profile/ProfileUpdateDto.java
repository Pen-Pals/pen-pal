package com.example.penpal.web.profile;

import com.example.penpal.domain.profile.entity.Profile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileUpdateDto {
    String profileUrl;
    String profileText;

    public Profile toEntity(String profileUrl, String profileText) {
        return Profile.builder()
                .profileUrl(profileUrl)
                .profileText(profileText)
                .build();
    }
}
