package com.test.swagger_springboot2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    //配置不同接口访问权限
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                // 增加swagger内容不需要basicauth权限可以直接访问
                .antMatchers("/swagger-ui/**", "/swagger-resources/**","/swagger-ui.html","/api/v2/api-docs","/webjars/**").permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .build();
    }
}
