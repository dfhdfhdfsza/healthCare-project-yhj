package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.dto.JoinDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Map;

public interface UserService {

  void addUser(JoinDTO joinDTO);

}
