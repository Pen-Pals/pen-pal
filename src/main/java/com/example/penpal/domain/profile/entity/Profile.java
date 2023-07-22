package com.example.penpal.domain.profile.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    private String profileUrl;
    private String profileText;

    @Builder
    public Profile(String profileUrl, String profileText) {
        this.profileUrl = profileUrl;
        this.profileText = profileText;
    }
}
