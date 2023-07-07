package com.example.penpal.domain.member.service;

import com.example.penpal.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

//    @Transactional
//    public MemberResponseDto signup(MemberRequestDto req) {
//        Member member = memberRepository.save(req.toEntity());
//        return MemberResponseDto.toDto(member);
//    }
}
