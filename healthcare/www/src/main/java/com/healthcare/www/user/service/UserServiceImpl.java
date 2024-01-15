package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public void joinProcess(JoinDTO joinDTO){

    String userName = joinDTO.getUserName();
    String userPassword = joinDTO.getUserPassword();

    Boolean isOk = userRepository.existsByUserName(userName);

    if(isOk){
      // 아이디가 존재한다면
      return;
    }
      // 아이디가 존재하지 않는다면
    User user = new User();
    user.setUserName(userName);
    user.setUserPassword(bCryptPasswordEncoder.encode(userPassword));
    user.setRole("ROLE_ADMIN");

    userRepository.save(user);
  }
}
