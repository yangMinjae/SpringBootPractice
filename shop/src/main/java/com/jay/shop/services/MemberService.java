package com.jay.shop.services;

import com.jay.shop.DTOs.MemberJoinDTO;
import com.jay.shop.entities.Member;
import com.jay.shop.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(MemberJoinDTO mDTO){
        if(memberRepository.existsByLoginId(mDTO.getLoginId())){
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(mDTO.getPassword());

        Member member = Member.builder()
                .loginId(mDTO.getLoginId())
                .password(encodedPassword)
                .name(mDTO.getName())
                .build();

        memberRepository.save(member);
    }
}
