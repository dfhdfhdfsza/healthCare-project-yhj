package com.healthcare.www.user.controller;

import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.service.UserService;
import com.healthcare.www.user.service.UserServiceImpl;
import jakarta.annotation.security.DenyAll;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequiredArgsConstructor
public class UserController {

    private final UserService userServiceImpl;
    private final UserService userService;

    @PostMapping("/user/signup")
    public String addSignup(JoinDTO jdto){
      userService.addUser(jdto);
      return "ok";
    }


}
