package com.example.penpal.web.member;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.service.AuthService;
import com.example.penpal.global.jwt.TokenDto;
import com.example.penpal.web.member.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Member")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Sign Up Method", description = "회원가입")
    @ApiResponse(responseCode = "200", description = "Successful",
            content = @Content(schema = @Schema(implementation = MemberResponseDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@Valid @RequestBody SignUpDto req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(req));
    }

    @Operation(summary = "Login Method", description = "로그인")
    @ApiResponse(responseCode = "200", description = "Successful")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberLoginRequestDto req) {
        TokenDto tokenDto = authService.login(req);
        return ResponseEntity.ok(tokenDto);
    }

    @Operation(summary = "My information", description = "내 정보 조회")
    @ApiResponse(responseCode = "200", description = "Successful")
    @GetMapping("/info")
    public ResponseEntity<MemberResponseDto> memberInfo() {
        MemberResponseDto result = authService.getMemberInfo();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Other information", description = "상대방 정보 조회")
    @ApiResponse(responseCode = "200", description = "Successful")
    @GetMapping("/info/{id}")
    public ResponseEntity<OtherMemberResponseDto> otherMemberInfo(@PathVariable Long id) {
        OtherMemberResponseDto result = authService.getMemberInfo(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<MemberResponseDto> editMember(@RequestBody MemberUpdateDto req) {
        MemberResponseDto result = authService.updateMember(req);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Delete information", description = "회원 탈퇴")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember() {
        authService.deleteMember();
        return new ResponseEntity<>( "You have deleted",HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<Member>> recommendMember() {
        return new ResponseEntity<>(authService.recommendByCountry(), HttpStatus.OK);
    }
}
