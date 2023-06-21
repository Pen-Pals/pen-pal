package com.example.penpal.domain.member.entity;

import com.example.penpal.domain.common.BaseTimeEntity;
import com.example.penpal.domain.favor.entity.Favor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthday;

    private String profileText;
    private String profileImage;
    private String location;

    @OneToMany(mappedBy = "member")
    private List<Favor> favors = new ArrayList<>();
    @Builder
    public Member(String email, String password, String nickname, Gender gender, LocalDate birthday, List<Favor> favors) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.favors = favors;
    }

}
