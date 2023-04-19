package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestLogbackController {

    @GetMapping("/logback")
    public String logback(){
        log.info("info级别，输出重要的信息，常用");
        log.debug("debug级别，调试，实际应用中一般将其作为最低级别");
        log.error("error级别，记录错误日志，常用");
        log.warn("warn级别，记录告警日志，常用");
        log.trace("trace级别，追踪，指明程序运行轨迹。很少用");
        int param1 = 1;
        String param2 = "param2";
        // 多个参数写入日志，可以通过{}占位符替换成参数
        log.info("传递参数: {}, 第二个参数: {}", param1, param2);
        return  "ok";
    }
}
