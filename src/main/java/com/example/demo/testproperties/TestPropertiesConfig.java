package com.example.demo.testproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通过类的方式获取
 * @ConfigurationProperties的prefix配置前缀
  */
@Component
@ConfigurationProperties(prefix = "test.properties.value")
public class TestPropertiesConfig {

    // 与key值（去掉前缀后）相同的配置
    private String a1;
    // 与key值（去掉前缀后）相同的配置
    private String b1;
    // 与key值（去掉前缀后）对应，配置文件为test-a，java按照驼峰配置testA即可获取
    private String testA;

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getTestA() {
        return testA;
    }

    public void setTestA(String testA) {
        this.testA = testA;
    }
}
