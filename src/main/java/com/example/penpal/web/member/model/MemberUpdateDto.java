package com.example.penpal.web.member.model;

import com.example.penpal.domain.member.entity.Authority;
import com.example.penpal.domain.member.entity.Gender;
import com.example.penpal.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberUpdateDto {
    private String email;
    private String password;
    private String nickname;
    Gender gender;
    LocalDate birthday;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .gender(gender)
                .birthday(birthday)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }


//    void update(String email, String password, String nickname, Gender gender, LocalDate birthday) {
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//        this.gender = gender;
//        this.birthday = birthday;
//    }
}
