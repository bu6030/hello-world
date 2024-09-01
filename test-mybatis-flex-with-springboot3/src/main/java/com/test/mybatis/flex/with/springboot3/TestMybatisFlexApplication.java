package com.test.mybatis.flex.with.springboot3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.test.mybatis.flex.with.springboot3.mapper")
@SpringBootApplication
public class TestMybatisFlexApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMybatisFlexApplication.class, args);
    }

}
