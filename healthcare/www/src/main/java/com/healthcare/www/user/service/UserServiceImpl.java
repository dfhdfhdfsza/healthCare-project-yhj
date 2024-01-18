package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.JoinDTO;
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




}
