package com.example.penpal.domain.member.service;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.web.member.model.MemberRequestDto;
import com.example.penpal.web.member.model.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
