package com.healthcare.www.user.service;

import com.healthcare.www.user.dto.JoinDTO;
import org.springframework.stereotype.Service;

public interface UserService {

  void addUser(JoinDTO joinDTO);
}
