package com.example.penpal.domain.letter.service;

import com.example.penpal.domain.letter.entity.Letter;
import com.example.penpal.domain.letter.repository.LetterRepository;
import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.repository.MemberRepository;
import com.example.penpal.global.exception.member.NotFoundLetterException;
import com.example.penpal.global.exception.member.NotFoundMemberException;
import com.example.penpal.web.letter.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;

    public SendLetterResponse sendLetter(SendLetterRequest request,Long sendId, Long receiveId) {
        Member member = memberRepository.findById(sendId).orElseThrow(NotFoundMemberException::new);
        Letter letter = SendLetterRequest.toEntity(request, member, receiveId);
        Letter savedLetter = letterRepository.save(letter);
        return SendLetterResponse.from(savedLetter);
    }

    public PageLetterListDto findLetters(Long sendId, Long receiveId, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 10, Sort.by("sendDate").descending());
        Page<Letter> page = letterRepository.findBySendIdAndReceiveId(sendId, receiveId, pageRequest);
        int totalPages = page.getTotalPages();
        List<LetterDto> letters = page.map(letter -> LetterDto.from(letter)).getContent();
        return PageLetterListDto.of(totalPages, letters);
    }

    public LetterListDto findRecentArrivedLetters(Long userId){
        List<LetterDto> letters = letterRepository.findRecentLetter(userId)
                .stream().map(l -> LetterDto.from(l))
                .collect(Collectors.toList());
        return LetterListDto.from(letters);
    }

    public LetterListDto findIncomingLetters(Long userId){
        List<LetterDto> letters = letterRepository.findIncomingLetter(userId)
                .stream().map(l -> LetterDto.from(l))
                .collect(Collectors.toList());
        return LetterListDto.from(letters);
    }

    public LetterDto findLetterDetail(Long userId, Long letterId){
        Letter letter = letterRepository.findByUserId(userId, letterId).orElseThrow(NotFoundLetterException::new);
        return LetterDto.from(letter);
    }
}
