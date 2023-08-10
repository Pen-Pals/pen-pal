package com.example.penpal.web.letter;

import com.example.penpal.domain.letter.service.LetterService;
import com.example.penpal.web.letter.model.LetterRemainTimeListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.penpal.global.security.SecurityUtil.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final LetterService letterService;

    @GetMapping
    public ResponseEntity<LetterRemainTimeListDto> remainTime(){
        LetterRemainTimeListDto letterRemainTimeListDto = letterService.calculateRemainingTime(getCurrentMemberId());
        return ResponseEntity.ok(letterRemainTimeListDto);
    }
}
