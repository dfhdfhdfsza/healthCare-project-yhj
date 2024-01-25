package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.CustomUserDetails;
import com.healthcare.www.user.repository.UserRepository;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    //DB에서 조회하는 구문
    User user = userRepository.findByUserId(username);

    if(user != null){
      //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
      return new CustomUserDetails(user);
    }


    return null;
  }
}
