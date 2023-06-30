package com.example.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DemoController {

    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public Object helloWorld() {
        return "Hello World!";
    }
    // 下面配置对/helloWorld1接口需要验证 ADMIN 的 authoritie
    // 和 SecurityConfig 中的 authorizeHttpRequests 代码的配置效果一样
    // 这两种方式用哪一种都可以
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/helloWorld1", method = RequestMethod.GET)
    public Object helloWorld1() {
        return "Hello World1!";
    }
}
