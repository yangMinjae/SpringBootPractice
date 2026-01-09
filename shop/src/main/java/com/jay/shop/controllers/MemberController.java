package com.jay.shop.controllers;

import com.jay.shop.DTOs.MemberJoinDTO;
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
    public ResponseEntity<String> register(@RequestBody MemberJoinDTO mDTO){
        System.out.println(mDTO.getName());
        System.out.println(mDTO.getLoginId());
        System.out.println(mDTO.getPassword());
        memberService.saveMember(mDTO);
        return ResponseEntity.ok("회원가입 성공");
    }
}
