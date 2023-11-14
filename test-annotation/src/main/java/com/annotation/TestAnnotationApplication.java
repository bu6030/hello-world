package com.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TestAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAnnotationApplication.class, args);
    }

}
