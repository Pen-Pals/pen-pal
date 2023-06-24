package com.example.penpal.domain.member.service;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.entity.RefreshToken;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.domain.member.repository.RefreshTokenRepository;
import com.example.penpal.global.exception.member.DuplicateEmailException;
import com.example.penpal.global.jwt.TokenDto;
import com.example.penpal.global.jwt.TokenProvider;
import com.example.penpal.web.member.model.MemberLoginRequestDto;
import com.example.penpal.web.member.model.MemberRequestDto;
import com.example.penpal.web.member.model.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto req) {

        if (memberRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException();
        }

        Member member = req.toEntity(passwordEncoder);
        return MemberResponseDto.toDto(memberRepository.save(member));

    }

    @Transactional
    public TokenDto login(MemberLoginRequestDto req) {
        UsernamePasswordAuthenticationToken authenticationToken = req.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

}
