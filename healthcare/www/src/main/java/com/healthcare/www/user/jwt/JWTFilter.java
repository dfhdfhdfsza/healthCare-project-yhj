package com.healthcare.www.user.jwt;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {

  private final JWTUtil jwtUtil;

  public JWTFilter(JWTUtil jwtUtil){
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    //request에서 Authorization 헤더를 찾음
    String authorization= request.getHeader("Authorization");

    //Authorization 헤더 검증
    if (authorization == null || !authorization.startsWith("Bearer ")) {

      filterChain.doFilter(request, response);

      //조건이 해당되면 메소드 종료 (필수)
      return;
    }

    // Bearer / 부분 제거하고 순수 토큰만 추출
    String token = authorization.split(" ")[1];
    log.info(token+"토큰<<<<<<<<<<<<<123123");

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

    // user에 삽입
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
