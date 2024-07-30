package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final static String ACCOUNT_CLIENT_AUTHORITY = "ADMIN";

    // 通过数据库配置basicauth/登录账号
    DataSource dataSource;
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    // 通过内存配置basicauth账号密码
//    @Bean
//    UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
//        users.createUser(User.withUsername("aaa")
//                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("bbb"))
//                .authorities(ACCOUNT_CLIENT_AUTHORITY).build());
//        return users;
//    }

    // 配置不同接口访问权限，以及登录页面配置
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 下面配置对/helloWorld1接口需要验证 ADMIN 的 authoritie
                // 和 Controller 中的 @PreAuthorize("hasAuthority('ADMIN')")注解配置效果一样
                // 这两种方式用哪一种都可以
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/helloWorld1").hasAuthority(ACCOUNT_CLIENT_AUTHORITY))
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/**").permitAll())
                // 下面两种，既可以自定义登录页面，也可以指定自定义登录页面
                // SpringBoot自带登录页面
                //.formLogin(withDefaults())
                // 自定义login页面
                .formLogin(form -> form
                        .loginPage("/login")  // 指定自定义登录页面
                        .permitAll()
                )
                // 允许通过basicauth请求API接口
                .httpBasic(withDefaults())
                .build();
    }
}
