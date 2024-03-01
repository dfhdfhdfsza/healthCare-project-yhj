package com.healthcare.www.common.controller;


import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.domain.UserFile;
import com.healthcare.www.user.repository.UserRepository;
import com.healthcare.www.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

  private final UserService userService;
  private final UserRepository userRepository;

//  @GetMapping("/")
//  public String profileIndex(Model m, @AuthenticationPrincipal UserDetails userDetails){
//    /* 현재 로그인한사람 */
//    User user = userRepository.findByUserId(userDetails.getUsername());
//    UserFile file = userService.selectUserFile(user.getUserNo());
//
//    m.addAttribute("userProfile",file);
//    return "/common/nav";
//  }

}
