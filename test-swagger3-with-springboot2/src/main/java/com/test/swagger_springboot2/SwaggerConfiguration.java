package com.test.swagger_springboot2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    // 控制是否允许swagger
    private boolean enableSwagger = true;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) //展示的swagger文档格
                .enable(enableSwagger) //是否启动swagger配置
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
