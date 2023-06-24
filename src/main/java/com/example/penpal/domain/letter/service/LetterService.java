package com.example.penpal.domain.letter.service;

import com.example.penpal.domain.letter.entity.Letter;
import com.example.penpal.domain.letter.repository.LetterRepository;
import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.global.exception.member.NotFoundMemberException;
import com.example.penpal.web.letter.model.LetterListDto;
import com.example.penpal.web.letter.model.PageLetterDto;
import com.example.penpal.web.letter.model.SendLetterRequest;
import com.example.penpal.web.letter.model.SendLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return SendLetterResponse.from(savedLetter);
    }

    public LetterListDto findLetters(Long sendId, Long receiveId, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 10, Sort.by("sendDate").descending());
        Page<Letter> page = letterRepository.findLettersByReceiveId(sendId, receiveId, pageRequest);
        int totalPages = page.getTotalPages();
        List<PageLetterDto> letters = page.map(letter -> PageLetterDto.from(letter)).getContent();
        return LetterListDto.of(totalPages, letters);
    }
}
