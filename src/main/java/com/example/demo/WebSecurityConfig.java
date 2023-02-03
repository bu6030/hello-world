package com.example.demo;

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

    private final static String ACCOUNT_CLIENT_AUTHORITY = "admin";

    //配置basicauth账号密码
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        users.createUser(User.withUsername("aaa")
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("bbb"))
                .authorities(ACCOUNT_CLIENT_AUTHORITY).build());
        return users;
    }
    //配置不同接口访问权限
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/helloWorld").permitAll()
                // 增加swagger内容不需要basicauth权限可以直接访问
                .antMatchers("/swagger-resources/**","/swagger-ui.html","/api/v2/api-docs","/webjars/**").permitAll()
                // 增加actuator内容不需要basicauth权限可以直接访问
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**", "/settings/**").hasAuthority(ACCOUNT_CLIENT_AUTHORITY).anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .build();
    }
}
