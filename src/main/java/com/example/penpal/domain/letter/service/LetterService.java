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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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

    public CorrespondentListDto findCorrespondents(Long userId){
        // 대화한 유저 목록
        List<Member> senders = memberRepository.findSendersByReceiveId(userId);
        List<Member> receivers = memberRepository.findReceiversBySendId(userId);
        List<Member> correspondents = Stream.of(senders, receivers)
                .flatMap(Collection::stream)
                .distinct()
                .collect(toList());

        List<CorrespondentDto> defaultCorrespondentDtos = correspondents.stream()
                .map(m -> CorrespondentDto.of(0, m))
                .collect(toList());

        // 유저가 아직 읽지 않은 Letter 개수
        List<UnreadCountInterface> counts = letterRepository.countUnreadLetterByReceiver(userId);

        // 유저가 읽지않은 편지 개수 적용
        List<CorrespondentDto> correspondentDtos  = defaultCorrespondentDtos.stream()
                .map(correspondentDto -> {
                    Optional<UnreadCountInterface> matchingCount = counts.stream()
                            .filter(count -> count.getMember().equals(correspondentDto.getMember()))
                            .findFirst();
                    return matchingCount.map(count -> CorrespondentDto.of(count.getUnreadCount(), count.getMember()))
                            .orElse(correspondentDto);
                }).collect(toList());

        return CorrespondentListDto.from(correspondentDtos);
    }

    public LetterListDto findRecentArrivedLetters(Long userId){
        List<LetterDto> letters = letterRepository.findRecentLetter(userId)
                .stream().map(l -> LetterDto.from(l))
                .collect(toList());
        return LetterListDto.from(letters);
    }

    public LetterListDto findIncomingLetters(Long userId){
        List<LetterDto> letters = letterRepository.findIncomingLetter(userId)
                .stream().map(l -> LetterDto.from(l))
                .collect(toList());
        return LetterListDto.from(letters);
    }

    public LetterDto findLetterDetail(Long userId, Long letterId){
        Letter letter = letterRepository.findByUserId(userId, letterId).orElseThrow(NotFoundLetterException::new);
        return LetterDto.from(letter);
    }

    public void updateReadStatus(Long letterId){
        letterRepository.updateReadStatus(letterId);
    }

    public void removeLetters(Long userId, Long otherUserId){
        letterRepository.deleteAllLetter(userId, otherUserId);
    }
}
