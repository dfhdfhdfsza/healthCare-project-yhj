package com.healthcare.www.user.controller;

import com.healthcare.www.user.service.UserServiceImpl;
import jakarta.annotation.security.DenyAll;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/**")
@Controller
public class UserController {


  @GetMapping("/user/login")
  public String moveLogin(){
    return "user/login";
  }

  @GetMapping("/user/signup")
  public String moveSignUp(){
    return "user/signup";
  }

}
