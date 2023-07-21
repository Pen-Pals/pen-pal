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
public class MemberRequestDto {
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private LocalDate birthday;
    private String address;


    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .gender(gender)
                .birthday(birthday)
                .authority(Authority.ROLE_USER)
                .address(address)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
