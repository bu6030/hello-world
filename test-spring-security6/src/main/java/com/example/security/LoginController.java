package com.example.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * 对应 login.html
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

