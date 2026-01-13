package com.jay.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/register")
    public String registerPage(){
        return "/register.html";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/login.html";
    }
}
