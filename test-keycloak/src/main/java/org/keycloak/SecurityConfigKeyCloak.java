package org.keycloak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Keycloak对应的Security文件
 */
@Order(0)
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigKeyCloak implements WebMvcConfigurer {

    @Value("${logout.success.url}")
    private String logoutSuccessUrl;

    /**
     * 配置需要忽略的静态文件
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Order(0)
    SecurityFilterChain staticEndpoints(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/css/**", "/js/**", "/fonts/**", "/images/**", "/i/**", "/resources/**", "/my-image/**")
            .headers((headers) -> headers.cacheControl((cache) -> cache.disable()))
            .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .oauth2Client(withDefaults())
            .oauth2Login((oauth2Login) -> oauth2Login.tokenEndpoint(withDefaults())
                .userInfoEndpoint(withDefaults()))
            .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests(
                (authorizeHttpRequests) -> authorizeHttpRequests
                    .requestMatchers("/unauthenticated", "/oauth2/**", "/login/**", "/login.html").permitAll()
                    .anyRequest().hasAnyAuthority("OIDC_USER", "ADMIN"))
            .logout((logout) -> logout
                .logoutSuccessUrl(logoutSuccessUrl));
        return http.build();
    }
}