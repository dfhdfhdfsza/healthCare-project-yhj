package com.healthcare.www.user.controller;


import com.healthcare.www.handler.FileHandler;
import com.healthcare.www.handler.FileType;
import com.healthcare.www.user.domain.Community;
import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.domain.UserFile;
import com.healthcare.www.user.domain.UserInfo;
import com.healthcare.www.user.dto.*;
import com.healthcare.www.user.jwt.JWTUtil;
import com.healthcare.www.user.repository.UserInfoRepository;
import com.healthcare.www.user.repository.UserRepository;
import com.healthcare.www.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

  private final UserService userService;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JWTUtil jwtUtil;
  private final UserRepository userRepository;
  private final UserInfoRepository userInfoRepository;
  private final FileHandler fh;

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
    public String moveMyPage(HttpServletRequest request, Model m, @AuthenticationPrincipal UserDetails userDetails){
      int validInfo = 0;
      int validImage= 0;

      User user = userRepository.findByUserId(userDetails.getUsername());
      m.addAttribute("user",user);


      UserInfo info = userService.selectUserInfo(user.getUserNo());
      System.out.println(info+"유저 정보<<");
      if(info == null){
        validInfo = 1;
        //유저가 정보를 입력하지 않았을 경우
        m.addAttribute("validInfo",validInfo);
      }else{
        // 입력한 경우 정보를 화면에 출력
        m.addAttribute("validInfo",validInfo);
        m.addAttribute("info",info);
      }

      UserFile file = userService.selectUserFile(user.getUserNo());
      System.out.println(file);

      if(file == null){
        // 이미지 없는 경우 기본 이미지 출력
        validImage = 1;
        m.addAttribute("validImage", validImage);
      }else{
        // 이미지가 있는 경우 이미지 출력
        m.addAttribute("validImage",validImage);
        m.addAttribute("file",file);
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
    String jwtToken =jwtUtil.createJWT(user.getUserId(),user.getUserRole(),expireTimeMs);

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

  @PostMapping("/information")
  public String postMyPage(UserInfoDTO userInfo){
    userService.addUserInfo(userInfo);

    return "index";
  }
  @GetMapping("/information")
  public String moveInformation(@RequestParam("userNo") long userNo,Model model){
    model.addAttribute("userNo",userNo);
    return "/user/information";
  }

  @GetMapping("/modify")
  public String moveModify(@RequestParam("userNo") long userNo , Model model){
    UserInfo info = userInfoRepository.findByUserNo(userNo);
    model.addAttribute("info",info);

    return "/user/modify";
  }
  @PostMapping("/modfiy")
  public String putModify(UserInfoDTO userInfoDTO){
    UserInfo info = userService.putUserInfo(userInfoDTO);

    return "redirect:/user/myPage";
  }

  @PostMapping("/profileImage")
  public String addImage(@RequestParam(name ="file" ,required = false)MultipartFile[] files, @AuthenticationPrincipal UserDetails userDetails){
    if(files[0].getSize() > 0){
      UserFileDTO userFileDTOS = fh.uploadUserFile(files,FileType.USER);

      User user = userRepository.findByUserId(userDetails.getUsername());

      int isOk = userService.addImage(userFileDTOS, user);
    }

    return "index";
  }

  @GetMapping("/community")
  public String moveCommunity(@AuthenticationPrincipal UserDetails userDetails,  Model model){
    User user = userRepository.findByUserId(userDetails.getUsername());
    model.addAttribute("user",user);

    List<Community> communityList = userService.selectAll();
    System.out.println(communityList.get(0));
    model.addAttribute("cList",communityList);

    return "/user/community";
  }

  @PostMapping("/community")
  public  String addCommunity(CommunityDTO communityDTO){
    Community community = userService.addCommunity(communityDTO);
    return "index";
  }
  @GetMapping("/communityDetail")
  public String moveCommunityDetail(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                    @RequestParam("writingNo") long writingNo){

    // 현재 로그인 정보 (댓글용도)
    User user = userRepository.findByUserId(userDetails.getUsername());
    model.addAttribute("user",user);

    //작성자 정보
    Community community = userService.selectCommunity(writingNo);
    model.addAttribute("community",community);

    return "/user/communityDetail";
  }
}
