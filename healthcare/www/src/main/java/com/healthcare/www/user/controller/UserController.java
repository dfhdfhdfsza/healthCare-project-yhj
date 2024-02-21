package com.healthcare.www.user.controller;


import com.healthcare.www.handler.FileHandler;
import com.healthcare.www.handler.FileType;
import com.healthcare.www.membership.domain.Membership;
import com.healthcare.www.order.domain.Payment;
import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductFile;
import com.healthcare.www.user.domain.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

      /* 현재 로그인한사람 */
      User user = userRepository.findByUserId(userDetails.getUsername());
      m.addAttribute("user",user);

      /* 주문 내역 */
      List<Payment> paymentList = userService.selectProduct(user.getUserId());
      m.addAttribute("paymentList", paymentList);

      /* 멤버쉽 / 포인트 조회용도 */
      Membership membership = userService.selectMembership(user.getUserId());
      m.addAttribute("membership",membership);

      /* 상품 내역 */
      List<Product> productList = userService.selectProductList(user.getUserId());
      m.addAttribute("productList",productList);


      /* 상품 파일 */
      List<ProductFile> productFileList = userService.selectProductFile(user.getUserId());
      m.addAttribute("productFileList",productFileList);


      /*유저정보*/
      UserInfo info = userService.selectUserInfo(user.getUserNo());
      m.addAttribute("info",info);

      /*이미지*/
      UserFile file = userService.selectUserFile(user.getUserNo());
      m.addAttribute("file",file);

      /*댓글*/
      List<Community> communityList = userService.selectCommunityList(user.getUserNo());
      m.addAttribute("communityList",communityList);



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

  @GetMapping("/modify")
  public String moveModify(@RequestParam("userNo") long userNo , Model model){
    UserInfo info = userInfoRepository.findByUserNo(userNo);
    model.addAttribute("info",info);

    return "/user/modify";
  }
  @PostMapping("/modify")
  public String putModify(UserInfoDTO userInfoDTO){
    UserInfo info = userService.putUserInfo(userInfoDTO);

    return "redirect:/user/myPage";
  }

  @PostMapping("/profileImage")
  public String addImage(@RequestParam(name ="file" ,required = false)MultipartFile[] files, @AuthenticationPrincipal UserDetails userDetails){
    /* 이미지 등록 */
    if(files[0].getSize() > 0){
      UserFileDTO userFileDTOS = fh.uploadUserFile(files,FileType.USER);

      User user = userRepository.findByUserId(userDetails.getUsername());

      int isOk = userService.addImage(userFileDTOS, user);
    }

    return "index";
  }

  @GetMapping("/community")
  public String moveCommunity(@AuthenticationPrincipal UserDetails userDetails,  Model model){
    /* 게시판 페이지로 이동 */

    User user = userRepository.findByUserId(userDetails.getUsername());
    model.addAttribute("user",user);

    List<Community> communityList = userService.selectAll();

    model.addAttribute("cList",communityList);

    return "/user/community";
  }

  @PostMapping("/community")
  public  String addCommunity(CommunityDTO communityDTO,
                              @RequestParam(name ="file" ,required = false)MultipartFile[] files,
                              @AuthenticationPrincipal UserDetails userDetails){
    /* 게시글 작성 */

    User user = userRepository.findByUserId(userDetails.getUsername());

    Community community = userService.addCommunity(communityDTO);

    if(files[0].getSize() > 0){
      CommunityFileDTO communityFileDTO = fh.uploadCommunityFile(files,FileType.COMMUNITY);
      /* 커뮤니티 파일 등록 */
      int isOk = userService.addCommunityFile(communityFileDTO, community, user);
    }
    return "redirect:/user/community";
  }
  @GetMapping("/communityDetail")
  public String moveCommunityDetail(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                    @RequestParam("writingNo") long writingNo){

    // 현재 로그인 정보 (댓글용도)
    User user = userRepository.findByUserId(userDetails.getUsername());
    model.addAttribute("user",user);

    // 게시글 작성자 정보
    Community community = userService.selectCommunity(writingNo);
    model.addAttribute("community",community);

    // 프로필사진
    UserFile userFile = userService.findByUserNo(community.getUserNo());
    model.addAttribute("file",userFile);

    // 커뮤니티 사진
    CommunityFile communityFile = userService.findByWritingNo(community.getWritingNo());
    model.addAttribute("cFile",communityFile);

    // 댓글 추천
    List<CommentFavorite> favoriteList = userService.selectFavoriteList(user.getUserNo(), writingNo);
    model.addAttribute("fList",favoriteList);

    List<Comment> commentList = userService.selectCommentList(writingNo);

    if(commentList != null){
      /* 댓글이 있는 경우 */
      model.addAttribute("commentList",commentList);
    }

    int validNumber = 0;

    if(user.getUserNo() != community.getUserNo()){
      validNumber = 1;
      model.addAttribute("validNumber",validNumber);
    }else{
      /* 현재 접속한 유저가 커뮤니티 작성자인 경우 */
      model.addAttribute("validNumber",validNumber);
    }

    // 조회수 추가



    return "/user/communityDetail";
  }
  @GetMapping("/communityDelete")
  public String deleteCommunity(@RequestParam("writingNo") long writingNo){
    /* 댓글 삭제 */

    userService.deleteCommunity(writingNo);

    return "index";
  }

  @PostMapping("/addComment")
  public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDTO){
    System.out.println(commentDTO.getUserNo());
    /* 댓글 등록 */
    Comment comment = userService.addComment(commentDTO);

    return comment != null ? new ResponseEntity<String>("1", HttpStatus.OK) :
        new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/commentDelete")
  public String deleteComment(@RequestParam long commentNo){
    /* 댓글 삭제 */
    Comment comment = userService.commentDelete(commentNo);


    return "redirect:/user/communityDetail?writingNo="+comment.getWritingNo();
  }



  @GetMapping(value = "/selectTag/{tag}" , produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Community>> searchTag(@PathVariable String tag){
    /* 태그 클릭 */
    List<Community> list = userService.selectTag(tag);

    return new ResponseEntity<List<Community>>(list,HttpStatus.OK);
  }

  @GetMapping(value = "/searchCommunity/{searchValue}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Community>> searchCommunity(@PathVariable String searchValue){
    /* 검색으로 */
    List<Community> list = userService.communityList(searchValue);


    return new ResponseEntity<List<Community>>(list,HttpStatus.OK);
  }

  @GetMapping("/favoriteAdd/{userNo}/{commentNo}")
  public ResponseEntity<String> addFavorite(@PathVariable long userNo,@PathVariable long commentNo){
    /* 댓글 추천 */
    int isOk = userService.addFavorite(userNo,commentNo);

    return new ResponseEntity<>("1", HttpStatus.OK);
  }

  @GetMapping("/userDelete")
  public String deleteUser(@RequestParam("userNo") long userNo){
    /* 회원 탈퇴 */
    userService.removeUser(userNo);

    return "redirect:/user/logout";
  }

  @GetMapping("/commentModify")
  public String commentModify(@RequestParam("commentNo") long commentNo, @RequestParam("commentContent") String commentContent){
    /* 댓글 수정 */
    userService.modifyComment(commentNo,commentContent);
    return "redirect:/user/community";
  }

  @DeleteMapping(value="/favoriteDelete/{userNo}/{commentNo}")
  public ResponseEntity<String> deleteFavorite(@PathVariable long userNo, @PathVariable long commentNo){
    System.out.println("유저번호/글번호"+userNo+"/"+commentNo);
    userService.removeFavorite(userNo,commentNo);

    return new ResponseEntity<>("1",HttpStatus.OK);
  }

  @PostMapping("/userModify")
  public String userModify(User user){
    userService.modifyUser(user);
    return "redirect:/user/myPage";
  }

  @GetMapping(value = "/selectProfileImage",produces =  MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<UserFile> selectProfileImage(@AuthenticationPrincipal UserDetails userDetails){
    User user = userRepository.findByUserId(userDetails.getUsername());
    if(user != null){
      UserFile file = userService.selectUserFile(user.getUserNo());
      return new ResponseEntity<>(file,HttpStatus.OK);
    }else{
      return new ResponseEntity<>(null,HttpStatus.OK);
    }

  }
}
