package com.example.penpal.domain.member.entity;

import com.example.penpal.domain.common.BaseTimeEntity;
import com.example.penpal.domain.favor.entity.Favor;
import com.example.penpal.web.member.model.MemberUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String address;

    private String profileText;
    private String profileImage;
    private String location;
    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member")
    private List<Favor> favors = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname, Gender gender, LocalDate birthday, Authority authority, String address) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.authority = authority;
        this.address = address;
    }

    public void update(MemberUpdateDto req, PasswordEncoder passwordEncoder) {
        this.email = req.getEmail();
        this.password = passwordEncoder.encode(req.getPassword());
        this.nickname = req.getNickname();
        this.gender = req.getGender();
        this.birthday = req.getBirthday();
    }

}
