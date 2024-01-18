package com.healthcare.www.user.controller;


import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.jwt.JWTUtil;
import com.healthcare.www.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


  private final JWTUtil jwtUtil;

  @GetMapping("signup")
    public String moveSignup(){
      // 회원가입 페이지로 이동
      return "/user/signup";
    }

    @GetMapping("login")
    public String moveLogin(){
      // 로그인 페이지로 이동
      return "/user/login";
    }

    @PostMapping("signup")
    public String addSignup(@Validated JoinDTO joinDTO, BindingResult bindingResult, Model model){
      if(bindingResult.hasErrors()){
        // 에러가 있으면 걸리는거
        return "/user/signup";
      }
      // 회원가입
      userService.addUser(joinDTO);
      return "index";
    }

    public ResponseEntity<String> login(){

    return new ResponseEntity<>("1", HttpStatus.OK);
    }


}
