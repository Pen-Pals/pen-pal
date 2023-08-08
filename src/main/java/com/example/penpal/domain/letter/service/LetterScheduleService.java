package com.example.penpal.domain.letter.service;

import com.example.penpal.domain.letter.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class LetterScheduleService {

    private final LetterRepository letterRepository;

    @Scheduled(fixedDelay = 60000)
    public void run(){
        letterRepository.updateArrivedStatus(LocalDateTime.now());
    }
}