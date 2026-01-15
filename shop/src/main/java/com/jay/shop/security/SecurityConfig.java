package com.jay.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity   // @PreAuthorize 사용 위한 주석
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomFailureHandler customFailureHandler;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.disable());
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
        );
        http.formLogin((formLogin)->
                formLogin.loginPage("/login")   // login 화면 url
                        .loginProcessingUrl("/login")   // form login이 데이터를 제출하는 url
                                                        // 설정을 안해주면 디폴트로 loginPage와 같은 주소
                        .defaultSuccessUrl("/")
                        .failureHandler(customFailureHandler)
                        .permitAll()                    // 로그인, 로그인 실패 페이지는 모두에게 허용
        );
        http
                // ... 다른 설정들
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 처리 URL (기본값이지만 명시 가능)
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 페이지
                        .invalidateHttpSession(true) // 세션 삭제
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
                );
        return http.build();
    }
}