package com.backend.AIResume.config;

import com.backend.AIResume.jwt.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // SECURITY FILTER
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/user/save",
                                "/user/login",
                                "/user/test",
                                "/resume/upload",
                                "/error",
                                "/resume/all",
                                "/resume/download/**",
                                "/resume/match/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/resume/rank",
                                "/resume/dashboard",
                                "/resume/recent",
                                "/note/save",
                                "/note/all",
                                "/note/resume/**"

                        ).permitAll()

                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}

//@Bean
//public WebSecurityCustomizer webSecurityCustomizer() {
//    return (web) -> web.ignoring().requestMatchers("/resume/upload", "/resume/**");
//
//}
