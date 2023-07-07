package com.example.penpal.web.member;

import com.example.penpal.domain.member.entity.Member;
import com.example.penpal.domain.member.service.AuthService;

import com.example.penpal.global.jwt.TokenDto;
import com.example.penpal.web.member.model.MemberLoginRequestDto;
import com.example.penpal.web.member.model.MemberRequestDto;
import com.example.penpal.web.member.model.MemberResponseDto;
import com.example.penpal.web.member.model.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(req));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberLoginRequestDto req){
        TokenDto tokenDto = authService.login(req);
        return ResponseEntity.ok(tokenDto);
    }

    @GetMapping("/info")
    public ResponseEntity<MemberResponseDto> memberInfo() {
        MemberResponseDto result = authService.getMemberInfo();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/edit")
    public ResponseEntity<MemberResponseDto> editMember(@RequestBody MemberUpdateDto req) {
        MemberResponseDto result = authService.updateMember(req);
        return ResponseEntity.ok(result);
    }
}