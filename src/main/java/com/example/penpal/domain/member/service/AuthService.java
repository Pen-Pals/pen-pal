package com.example.penpal.domain.member.service;

import com.example.penpal.domain.favor.entity.Favor;
import com.example.penpal.domain.favor.repository.FavorRepository;
import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.entity.RefreshToken;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.domain.member.repository.RefreshTokenRepository;
import com.example.penpal.global.exception.member.DuplicateEmailException;
import com.example.penpal.global.jwt.TokenDto;
import com.example.penpal.global.jwt.TokenProvider;
import com.example.penpal.global.security.SecurityUtil;
import com.example.penpal.web.member.model.*;
import jakarta.validation.NoProviderFoundException;
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
    private final FavorRepository favorRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public MemberResponseDto signup(SignUpDto req) {

        if (memberRepository.existsByEmail(req.getMember().getEmail())) {
            throw new DuplicateEmailException();
        }
        Favor favor = favorRepository.save(req.getFavor().toDto());
        Member member = req.getMember().toEntity(passwordEncoder);
        memberRepository.save(member);
        member.updateFavors(favor);
        return MemberResponseDto.toDto(member);
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

    // 개인정보 조회
    public MemberResponseDto getMemberInfo(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(NoProviderFoundException::new);
        System.out.println("member = " + member);
        return MemberResponseDto.toDto(member);
    }

    @Transactional
    public MemberResponseDto updateMember(MemberUpdateDto req) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(NoProviderFoundException::new);
        member.update(req, passwordEncoder);
        return MemberResponseDto.toDto(member);
    }

}


