package com.example.demo.controller;

import com.example.demo.testproperties.TestPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestPropertiesController {

    @Autowired
    private TestPropertiesConfig testPropertiesConfig;

    // 直接通过具体配置文件配置的key获取
    @Value("${test.properties.value.a}")
    private String a;
    // 直接通过具体配置文件配置的key获取
    @Value("${test.properties.value.b}")
    private String b;

    @Value("${test.properties.value.bb1}")
    private String bb1;

    @GetMapping("/properties")
    public String getProperties(){
        log.info("a={};b={}", a, b);
        return  "a=" + a + ";b=" + b;
    }

    @GetMapping("/properties1")
    public String getProperties1(){
        log.info("a1={};b1={};test-a={},bb1={}", testPropertiesConfig.getA1(),
                testPropertiesConfig.getB1(), testPropertiesConfig.getTestA(), bb1);
        return  "a1=" + testPropertiesConfig.getA1() + ";b1="
                + testPropertiesConfig.getB1() + ";test-a="
                + testPropertiesConfig.getTestA() + ";bb1=" + bb1;
    }
}
