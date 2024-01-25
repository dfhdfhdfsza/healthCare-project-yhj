package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.domain.UserInfo;
import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.dto.LoginDTO;
import com.healthcare.www.user.dto.UserInfoDTO;
import com.healthcare.www.user.repository.UserInfoRepository;
import com.healthcare.www.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final UserInfoRepository userInfoRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void addUser(JoinDTO joinDTO) {
    String userName = joinDTO.getUserId();

    Boolean isOk = userRepository.existsByUserName(userName);

    if(isOk){
      // 아이디가 존재한다면
      return;
    }
    // 아이디가 존재하지 않는다면
    User user = User.builder().
        userId(joinDTO.getUserId()).
        userPassword(bCryptPasswordEncoder.encode(joinDTO.getUserPassword())).
        userName(joinDTO.getUserName()).
        userAddress(joinDTO.getUserAddress()).
        userAge(joinDTO.getUserAge()).
        userNumber(joinDTO.getUserNumber()).
        userMail(joinDTO.getUserMail()).
        userRole("ROLE_ADMIN").
        build();

    userRepository.save(user);
  }

  @Override
  public User login(LoginDTO loginDTO) {

    User user = userRepository.findByUserId(loginDTO.getId());

    if(user == null){
      // 존재하지 않는 계정일 경우
      System.out.println("존재하지 않는 계정");
        return null;
    }

    //String checkPassword = bCryptPasswordEncoder.encode(loginDTO.getPwd());

    if(!bCryptPasswordEncoder.matches(loginDTO.getPwd(),user.getUserPassword())){
      return null;
    }



    return user;
  }

  @Override
  public User getUserInfomation(String userInfo) {
    User user = userRepository.findByUserName(userInfo);

    return user;
  }

  @Override
  public void addUserInfo(UserInfoDTO userInfo) {
    UserInfo info = new UserInfo();
    info.setUserNo(userInfo.getUserNo());
    info.setInfoHeight(userInfo.getInfoHeight());
    info.setInfoWeight(userInfo.getInfoWeight());
    info.setInfoMetabolic(userInfo.getInfoMetabolic());
    info.setInfoBody(userInfo.getInfoBody());
    info.setInfoProfile(userInfo.getInfoProfile());
    info.setInfoSkeletal(userInfo.getInfoSkeletal());
    userInfoRepository.save(info);

  }

  @Override
  public UserInfo selectUserInfo(long userNo) {
    UserInfo info = userInfoRepository.findByUserNo(userNo);
    return info;
  }


}
