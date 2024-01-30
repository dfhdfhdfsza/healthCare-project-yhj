package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.domain.UserFile;
import com.healthcare.www.user.domain.UserInfo;
import com.healthcare.www.user.dto.JoinDTO;
import com.healthcare.www.user.dto.LoginDTO;
import com.healthcare.www.user.dto.UserFileDTO;
import com.healthcare.www.user.dto.UserInfoDTO;

public interface UserService {

  void addUser(JoinDTO joinDTO);

  User login(LoginDTO loginDTO);

  User getUserInfomation(String userInfo);


  void addUserInfo(UserInfoDTO userInfo);

  UserInfo selectUserInfo(long userNo);


  UserInfo putUserInfo(UserInfoDTO userInfoDTO);

  int addImage(UserFileDTO userFileDTOS, User user);

  UserFile selectUserFile(long userNo);
}
