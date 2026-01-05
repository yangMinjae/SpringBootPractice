package com.jay.shop.controllers;

import com.jay.shop.DTOs.MemberJoinDTO;
import com.jay.shop.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<String> register(MemberJoinDTO mDTO){
        memberService.saveMember(mDTO);
        return ResponseEntity.ok("회원가입 성공");
    }
}
