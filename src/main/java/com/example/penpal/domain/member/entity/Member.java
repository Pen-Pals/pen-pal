package com.example.penpal.domain.member.entity;

import com.example.penpal.domain.common.BaseTimeEntity;
import com.example.penpal.domain.country.entity.Country;
import com.example.penpal.domain.favor.entity.Favor;
import com.example.penpal.domain.profile.entity.Profile;
import com.example.penpal.web.member.model.MemberUpdateDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private String country;

    @Enumerated(EnumType.STRING)
    private Authority authority;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "favor_id")
    private Favor favors;

    public void updateFavors(Favor favors) {
        this.favors = favors;
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
    }

    @Builder
    public Member(String email, String password, String nickname, Gender gender,
                  LocalDate birthday, String country ,Authority authority) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
        this.authority = authority;
    }

    public void update(MemberUpdateDto req, PasswordEncoder passwordEncoder) {
        this.email = req.getEmail();
        this.password = passwordEncoder.encode(req.getPassword());
        this.nickname = req.getNickname();
        this.gender = req.getGender();
        this.birthday = req.getBirthday();
    }

}
