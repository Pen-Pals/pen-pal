package com.example.penpal.domain.letter.service;

import com.example.penpal.domain.Member.entity.Member;
import com.example.penpal.domain.Member.repository.MemberRepository;
import com.example.penpal.domain.letter.entity.Letter;
import com.example.penpal.domain.letter.repository.LetterRepository;
import com.example.penpal.global.exception.member.NotFoundMemberException;
import com.example.penpal.web.letter.model.SendLetterRequest;
import com.example.penpal.web.letter.model.SendLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;

    public SendLetterResponse sendLetter(SendLetterRequest request, Long sendId) {
        Member member = memberRepository.findById(sendId).orElseThrow(NotFoundMemberException::new);
        Letter letter = SendLetterRequest.toEntity(request, member);
        Letter savedLetter = letterRepository.save(letter);
        return SendLetterResponse.from(letter);
    }
}
