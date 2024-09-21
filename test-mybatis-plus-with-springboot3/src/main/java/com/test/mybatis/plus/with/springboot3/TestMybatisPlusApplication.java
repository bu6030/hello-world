package com.test.mybatis.plus.with.springboot3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.test.mybatis.plus.with.springboot3.mapper")
@SpringBootApplication
public class TestMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMybatisPlusApplication.class, args);
    }

}
