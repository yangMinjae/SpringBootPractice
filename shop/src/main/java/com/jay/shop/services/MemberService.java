package com.jay.shop.services;

import com.jay.shop.DTOs.MemberDto;
import com.jay.shop.entities.Member;
import com.jay.shop.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(MemberDto.JoinRequest dto){
        if(memberRepository.existsByLoginId(dto.loginId())){
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = Member.builder()
                .loginId(dto.loginId())
                .password(encodedPassword)
                .name(dto.name())
                .build();

        memberRepository.save(member);
    }
}
