package com.example.penpal.web.letter;

import com.example.penpal.domain.letter.service.LetterService;
import com.example.penpal.web.letter.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.penpal.global.security.SecurityUtil.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {

    private final LetterService letterService;


    @PostMapping("/{userId}")
    public ResponseEntity<SendLetterResponse> send(@PathVariable("userId") Long receiveId,
                                                   @RequestBody SendLetterRequest request) {
        SendLetterResponse response = letterService.sendLetter(request, getCurrentMemberId(), receiveId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PageLetterListDto> letterList(@PathVariable("userId") Long receiveId,
                                                        Pageable pageable) {
        //나중에 인증에서 받아오기
        PageLetterListDto letters = letterService.findLetters(getCurrentMemberId(), receiveId, pageable);
        return ResponseEntity.ok(letters);
    }

    @GetMapping
    public ResponseEntity<CorrespondentListDto> correspondentList() {
        CorrespondentListDto correspondents = letterService.findCorrespondents(getCurrentMemberId());
        return ResponseEntity.ok(correspondents);
    }

    @GetMapping("/my/recent")
    public ResponseEntity<LetterListDto> recentLetterList() {
        LetterListDto letters = letterService.findRecentArrivedLetters(getCurrentMemberId());
        return ResponseEntity.ok(letters);
    }

    @GetMapping("/my/incoming")
    public ResponseEntity<LetterListDto> incomingLetterList() {
        LetterListDto letters = letterService.findIncomingLetters(getCurrentMemberId());
        return ResponseEntity.ok(letters);
    }

    @GetMapping("/{userId}/{letterId}")
    public ResponseEntity<LetterDto> letterDetails(@PathVariable Long userId,
                                                   @PathVariable Long letterId) {
        LetterDto letter = letterService.findLetterDetail(userId, letterId);
        return ResponseEntity.ok(letter);
    }

    @PatchMapping("/{letterId}")
    public ResponseEntity<Long> readStatusUpdate(@PathVariable Long letterId) {
        letterService.updateReadStatus(letterId);
        return ResponseEntity.ok(letterId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> lettersRemove(@PathVariable("userId") Long otherUserId) {
        letterService.removeLetters(getCurrentMemberId(), otherUserId);
        return ResponseEntity.ok(otherUserId);
    }
}
