package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AspectController {

    @GetMapping("/aspect/error")
    public String error() throws Exception{
        throw new RuntimeException("error");
    }

    @GetMapping("/aspect/test")
    public String test() throws Exception{
        log.info("进入test方法");
        return "ok";
    }
}
