package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.dto.LoginDTO;
import com.healthcare.www.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
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
    System.out.println("로그인 DTO 체크 =>"+loginDTO);
    User user = userRepository.findByUserName(loginDTO.getId());
    System.out.println("유저 정보 체크 => "+user);
    if(user == null){
      // 존재하지 않는 계정일 경우
      System.out.println("존재하지 않는 계정");
        return null;
    }
    if(bCryptPasswordEncoder.matches(user.getUserPassword(),loginDTO.getPwd())){
      // 비밀번호가 다른경우'
      System.out.println("비밀번호가 다름");
      return null;
    }



    return user;
  }


}
