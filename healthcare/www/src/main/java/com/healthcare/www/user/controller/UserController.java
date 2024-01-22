package com.healthcare.www.user.controller;


import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.CustomUserDetails;
import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.dto.LoginDTO;
import com.healthcare.www.user.jwt.JWTUtil;
import com.healthcare.www.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JWTUtil jwtUtil;

  private String jwtCookie;

  @GetMapping("/signup")
    public String moveSignup(){
      // 회원가입 페이지로 이동
      return "/user/signup";
    }

    @GetMapping("/login")
    public String moveLogin(LoginDTO loginDTO, Model m){
      // 로그인 페이지로 이동
      m.addAttribute("loginDTO",loginDTO);
      return "/user/login";
    }

    @GetMapping("/myPage")
    public String moveMyPage(HttpServletRequest request){

      Cookie[] cookies = request.getCookies();

      // 만약 쿠키가 있다면 jwtToken 쿠키의 값을 가져오기
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals("jwtToken")) {
            String jwtTokenValue = cookie.getValue();
            String userInfo = jwtUtil.getUsername(jwtTokenValue);

            System.out.println(userInfo);
          }
        }
      }




      return "/user/myPage";
    }
    @PostMapping("/signup")
    public String addSignup(@Validated JoinDTO joinDTO, BindingResult bindingResult, Model model){

      // 회원가입
      userService.addUser(joinDTO);

      return "index";
    }

  @PostMapping("/login")
  public String postLogin(LoginDTO loginDTO, HttpServletResponse response, Model model){


    // 로그인
    User user = userService.login(loginDTO);

    if(user == null){
      model.addAttribute("loginErr",1);
      return "/user/login";
    }


    // 로그인 성공 후 -> 토큰 발급
    long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분
    String jwtToken =jwtUtil.createJWT(user.getUserName(),user.getUserRole(),expireTimeMs);

    // 발급한 토큰을 Cookie를 통해서 전송
    // 클라이언트는 다음 요청부터 jwt 토큰이 담긴 쿠키를 전송 => 이 값으로 인증 , 인가
    Cookie cookie = new Cookie("jwtToken",jwtToken);
    cookie.setMaxAge(60 * 60); // 쿠키 유효 시간 : 1시간
    cookie.setPath("/");
    response.addCookie(cookie);


    return "index";
  }

  @GetMapping("/logout")
  public String logout(HttpServletResponse response){
    Cookie cookie = new Cookie("jwtToken", null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);
    return "redirect:/user/login";
  }



}
