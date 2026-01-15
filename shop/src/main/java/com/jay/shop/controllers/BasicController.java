package com.jay.shop.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/")
    public String main(){
        return "redirect:/list";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "/register.html";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/login.html";
    }

    @GetMapping("/my-page")
    @PreAuthorize("isAuthenticated()")
    public String myPage(Authentication auth){
        System.out.println("auth : "+auth);
        System.out.println("auth 이름 : "+auth.getName());
        System.out.println("auth 로그인 여부 : "+auth.isAuthenticated());
        System.out.println("ROLE_USER 포함 여부 : "+auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        return "/mypage.html";
    }
}
