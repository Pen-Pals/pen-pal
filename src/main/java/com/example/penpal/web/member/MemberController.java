package com.example.penpal.web.member;

import com.example.penpal.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

//    @PostMapping("/sign-up")
//    public ResponseEntity<MemberResponseDto> singUp(@RequestBody MemberRequestDto req) {
//        MemberResponseDto result = memberService.signup(req);
//        return new ResponseEntity(result, HttpStatus.CREATED);
//    }


}
