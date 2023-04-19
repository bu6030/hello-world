package com.example.demo.testproperties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:test-properties.properties", encoding = "UTF-8")
public class OtherPropertiesConfiguration {
}
