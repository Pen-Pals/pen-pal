package com.example.penpal.web.letter;

import com.example.penpal.domain.letter.service.LetterService;
import com.example.penpal.global.exception.model.BusinessExceptionResponse;
import com.example.penpal.web.letter.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.penpal.global.security.SecurityUtil.getCurrentMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {

    private final LetterService letterService;

    @Operation(summary = "특정 유저에게 편지쓰기", description = "편지보낸 시각, 도착 시각, 걸리는 시간과 편지 내용을 받아 편지 보내기")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "편지 전송 성공"),
            @ApiResponse(responseCode = "404", description = "특정 유저가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = BusinessExceptionResponse.class)))
    })
    @PostMapping("/{userId}")
    public ResponseEntity<SendLetterResponse> send(@Parameter(description = "받는 사람 Id", required = true, example = "3")
                                                   @PathVariable("userId") Long receiveId,
                                                   @RequestBody SendLetterRequest request) {
        SendLetterResponse response = letterService.sendLetter(request, getCurrentMemberId(), receiveId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "특정 유저와의 편지 목록 보기", description = "전체 페이지 수와 페이지당 10개의 편지를 가져옴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "편지 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "특정 유저가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = BusinessExceptionResponse.class)))
    })
    @GetMapping("/{userId}")
    public ResponseEntity<PageLetterListDto> letterList(@Parameter(description = "특정 유저 Id", required = true, example = "3")
                                                        @PathVariable("userId") Long receiveId,
                                                        Pageable pageable) {
        PageLetterListDto letters = letterService.findLetters(getCurrentMemberId(), receiveId, pageable);
        return ResponseEntity.ok(letters);
    }

    @Operation(summary = "대화한 사람들 목록보기", description = "대화한 사람들 목록과 사람들마다 내가 안읽은 편지 개수를 가져옴")
    @ApiResponse(responseCode = "200", description = "대화한 사람들 목록 조회 성공")
    @GetMapping
    public ResponseEntity<CorrespondentListDto> correspondentList() {
        CorrespondentListDto correspondents = letterService.findCorrespondents(getCurrentMemberId());
        return ResponseEntity.ok(correspondents);
    }

    @Operation(summary = "최근에 도착한 편지목록", description = "최근에 도착한 편지목록을 불러옴")
    @ApiResponse(responseCode = "200", description = "도착한 편지 목록 조회 성공")
    @GetMapping("/my/recent")
    public ResponseEntity<LetterListDto> recentLetterList() {
        LetterListDto letters = letterService.findRecentArrivedLetters(getCurrentMemberId());
        return ResponseEntity.ok(letters);
    }

    @Operation(summary = "오고 있는 편지목록", description = "오고 있는 편지목록을 불러옴")
    @ApiResponse(responseCode = "200", description = "오고 있는 편지 목록 조회 성공")
    @GetMapping("/my/incoming")
    public ResponseEntity<LetterListDto> incomingLetterList() {
        LetterListDto letters = letterService.findIncomingLetters(getCurrentMemberId());
        return ResponseEntity.ok(letters);
    }

    @Operation(summary = "특정 유저와의 특정 편지 확인", description = "특정 유저와의 특정 편지의 내용을 불러옴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "특정 편지 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "특정 유저가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = BusinessExceptionResponse.class)))
    })
    @GetMapping("/{userId}/{letterId}")
    public ResponseEntity<LetterDto> letterDetails(@Parameter(description = "특정 유저 Id", required = true, example = "3")
                                                   @PathVariable Long userId,
                                                   @Parameter(description = "편지 Id", required = true, example = "4")
                                                   @PathVariable Long letterId) {
        LetterDto letter = letterService.findLetterDetail(userId, letterId);
        return ResponseEntity.ok(letter);
    }

    @Operation(summary = "편지 읽음 처리", description = "특정 편지 읽음 처리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "편지 읽음 처리 성공"),
            @ApiResponse(responseCode = "404", description = "편지가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = BusinessExceptionResponse.class)))
    })
    @PatchMapping("/{letterId}")
    public ResponseEntity<Long> readStatusUpdate(@Parameter(description = "편지 Id", required = true, example = "3")
                                                 @PathVariable Long letterId) {
        letterService.updateReadStatus(letterId);
        return ResponseEntity.ok(letterId);
    }

    @Operation(summary = "편지 전체 삭제", description = "특정 유저와의 편지 전체 삭제")
    @ApiResponse(responseCode = "200", description = "편지 삭제 성공")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> lettersRemove(@Parameter(description = "특정 유저 Id", required = true, example = "3")
                                              @PathVariable("userId") Long otherUserId) {
        letterService.removeLetters(getCurrentMemberId(), otherUserId);
        return ResponseEntity.ok(otherUserId);
    }
}
