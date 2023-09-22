package com.test.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TestSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSwaggerApplication.class, args);
    }

}
