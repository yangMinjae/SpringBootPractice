package com.jay.shop.controllers;

import com.jay.shop.DTOs.MemberDto;
import com.jay.shop.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<String> register(@RequestBody MemberDto.JoinRequest dto){
        System.out.println(dto.name());
        System.out.println(dto.loginId());
        System.out.println(dto.password());
        memberService.saveMember(dto);
        return ResponseEntity.ok("회원가입 성공");
    }
}
