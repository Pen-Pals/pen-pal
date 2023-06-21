package com.example.penpal.web.letter;

import com.example.penpal.domain.letter.service.LetterService;
import com.example.penpal.web.letter.model.LetterListDto;
import com.example.penpal.web.letter.model.PageLetterDto;
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

    @PostMapping("/{userId}")
    public ResponseEntity<SendLetterResponse> send(@PathVariable("userId") Long sendId,
                                                   @RequestBody SendLetterRequest request){
        SendLetterResponse response = letterService.sendLetter(request, sendId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<LetterListDto> letterList(@PathVariable("userId") Long sendId,
                                                    @RequestBody Long receiveId,
                                                    Pageable pageable){
        LetterListDto letters = letterService.findLetters(sendId, receiveId, pageable);
        return ResponseEntity.ok(letters);
    }
}
