package com.healthcare.www.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig{

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    // 인가작업
    http.authorizeHttpRequests(auth->auth
        .requestMatchers("/","/signup","/login").permitAll()
        .requestMatchers("/admin").hasRole("ADMIN")
        .anyRequest().authenticated());


    return http.build();
  }




}
