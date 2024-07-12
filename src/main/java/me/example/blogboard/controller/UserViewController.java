package me.example.blogboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    // 로그인
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //회원가입
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}