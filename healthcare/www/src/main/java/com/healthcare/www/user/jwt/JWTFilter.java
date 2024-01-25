package com.healthcare.www.user.jwt;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {


  private final JWTUtil jwtUtil;


  public JWTFilter(JWTUtil jwtUtil ){
    this.jwtUtil=jwtUtil;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    //request에서 Authorization 헤더를 찾음
    String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

    if(authorization == null){

      // 화면 로그인 시 쿠키의 "jwtToken"로 Jwt Token을 전송
      // 쿠키에도 Jwt Token이 없다면 로그인 하지 않은 것으로 간주
      if(request.getCookies() == null){
        filterChain.doFilter(request, response);
        return;
      }

      // 쿠키에서 jwtToken이라는 쿠키가 없으면 리턴
      Cookie jwtTokenCookie = Arrays.stream(request.getCookies())
          .filter(cookie -> cookie.getName().equals("jwtToken"))
          .findFirst()
          .orElse(null);


      if(jwtTokenCookie == null){
        filterChain.doFilter(request, response);
        return;
      }

      //Authorization 헤더 검증
//      if (authorization == null || !authorization.startsWith("Bearer ")) {
//        System.out.println("헤더에 있는지 체크");
//        filterChain.doFilter(request, response);
//
//        //조건이 해당되면 메소드 종료 (필수)
//        return;
//      }
      String jwtToken =  jwtTokenCookie.getValue();
      authorization = "Bearer " + jwtToken;


    }


      // Bearer / 부분 제거하고 순수 토큰만 추출
      String token = authorization.split(" ")[1];

      //토큰 소멸 시간 검증
      if (jwtUtil.isExpired(token)) {
        // true면 시간 만료
        log.info("isExpired token expired");
        filterChain.doFilter(request, response);

        //조건이 해당되면 메소드 종료 (필수)
        return;
      }

      // 최종적으로 토큰 확인
      // 토큰에서 username , role 가져오기

      String username = jwtUtil.getUsername(token);
      String role = jwtUtil.getRole(token);

       //user에 삽입
      User user = new User();
      user.setUserName(username);
      user.setUserPassword("temppassword"); // 임시 비밀번호 생성
      user.setUserRole(role);




      // userdetails에 user정보 담기
      CustomUserDetails customUserDetails = new CustomUserDetails(user);

      //스프링 시큐리티 인증 토큰 생성
      Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

      // 세션에 사용자 등록
      SecurityContextHolder.getContext().setAuthentication(authToken);

      // 필터체인을 통해 다음 필터에게 requset response를 넘기기
      filterChain.doFilter(request,response);

  }
}
