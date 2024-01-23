package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.dto.LoginDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Map;

public interface UserService {

  void addUser(JoinDTO joinDTO);

  User login(LoginDTO loginDTO);

  User getUserInfomation(String userInfo);
}
