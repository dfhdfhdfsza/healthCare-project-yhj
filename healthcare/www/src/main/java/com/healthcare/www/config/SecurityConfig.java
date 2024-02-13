package com.healthcare.www.config;

import com.healthcare.www.user.jwt.JWTFilter;
import com.healthcare.www.user.jwt.JWTUtil;
import com.healthcare.www.user.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final AuthenticationConfiguration authenticationConfiguration;

  private final JWTUtil jwtUtil;

//  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,JWTUtil jwtUtil){
//    this.authenticationConfiguration=authenticationConfiguration;
//    this.jwtUtil=jwtUtil;
//  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{

    return configuration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    // 비밀번호 암호화
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .cors((cors) -> cors.configurationSource(apiConfigurationSource()));

    //csrf disable
    http
        .csrf((auth) -> auth.disable());

    //From 로그인 방식 disable
//    http
//        .formLogin((auth) -> auth.disable());

    //http basic 인증 방식 disable
    http
        .httpBasic((auth) -> auth.disable());

    //경로별 인가 작업
    http
        .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/user/login", "/", "/user/signup","/css/**","/image/**","/main/**",
                "/js/**","/health/**","/product/**","/login","/user/logout","/user/myPage","/food/**","/productFile/**",
                "/error", "/order/**","/userFile/**","/communityFile/**").permitAll()
            .requestMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated());

    // JWT 필터 등록
    http
        .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

    //LoginFilter 적용
    http
        .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

    //세션 설정 /세션을 STATELESS 상태로 설정
    http
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


    return http.build();
  }

  public CorsConfigurationSource apiConfigurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(Arrays.asList("https://api.example.com"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
    configuration.addExposedHeader("Authorization");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}
