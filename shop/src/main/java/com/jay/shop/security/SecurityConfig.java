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
        return http.build();
    }
}