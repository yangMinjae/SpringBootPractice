package com.jay.shop.security;

import com.jay.shop.entities.Member;
import com.jay.shop.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        // 이 예외는 GlobalExceptionHandler로 못잡는다. FailureHandler를 커스텀해서 에러 메시지를 html로 쿼리
        // 스트링에 담아 보낼 수 있다. Config에서 CustomFailureHandler를 등록해 주어야 한다.
        return CustomUser.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .name(member.getName())
                .build();
    }
}
