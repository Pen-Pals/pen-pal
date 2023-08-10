package com.example.penpal.web.letter;

import com.example.penpal.domain.letter.service.LetterService;
import com.example.penpal.web.letter.model.LetterRemainTimeListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "내게 오고있는 편지들 남은 배송시간 보기",
            description = "아직 나에게 도착하지 않은 편지들의 남은 일, 시간, 분을 알려줌. 배송완료된(남은 시간이 0인) 편지는 표시되지 않음")
    @ApiResponse(responseCode = "200", description = "오고 있는 편지 조회 성공")
    @GetMapping
    public ResponseEntity<LetterRemainTimeListDto> remainTime(){
        LetterRemainTimeListDto letterRemainTimeListDto = letterService.calculateRemainingTime(getCurrentMemberId());
        return ResponseEntity.ok(letterRemainTimeListDto);
    }
}
