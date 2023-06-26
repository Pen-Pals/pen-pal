package com.example.penpal.web.letter;

import com.example.penpal.domain.letter.service.LetterService;
import com.example.penpal.web.letter.model.LetterListDto;
import com.example.penpal.web.letter.model.PageLetterListDto;
import com.example.penpal.web.letter.model.SendLetterRequest;
import com.example.penpal.web.letter.model.SendLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {

    private final LetterService letterService;

    //나중에 인증에서 받아와서 교체
    Long sendId = 1L;
    Long userId = 2L;

    @PostMapping("/{userId}")
    public ResponseEntity<SendLetterResponse> send(@PathVariable("userId") Long receiveId,
                                                   @RequestBody SendLetterRequest request){
        SendLetterResponse response = letterService.sendLetter(request, sendId, receiveId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PageLetterListDto> letterList(@PathVariable("userId") Long receiveId,
                                                        Pageable pageable){
        //나중에 인증에서 받아오기
        PageLetterListDto letters = letterService.findLetters(sendId, receiveId, pageable);
        return ResponseEntity.ok(letters);
    }

    @GetMapping("/my/recent")
    public ResponseEntity<LetterListDto> recentLetterList(){
        LetterListDto letters = letterService.findRecentArrivedLetters(userId);
        return ResponseEntity.ok(letters);
    }
}
