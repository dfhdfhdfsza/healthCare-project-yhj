package com.healthcare.www.user.service;

import com.healthcare.www.order.domain.Payment;
import com.healthcare.www.user.domain.*;
import com.healthcare.www.user.dto.*;

import java.util.List;

public interface UserService {

  void addUser(JoinDTO joinDTO);

  User login(LoginDTO loginDTO);

  User getUserInfomation(String userInfo);


  void addUserInfo(UserInfoDTO userInfo);

  UserInfo selectUserInfo(long userNo);


  UserInfo putUserInfo(UserInfoDTO userInfoDTO);

  int addImage(UserFileDTO userFileDTOS, User user);

  UserFile selectUserFile(long userNo);

  Community addCommunity(CommunityDTO communityDTO);


  List<Community> selectAll();

  Community selectCommunity(long writingNo);

  Comment addComment(CommentDTO commentDTO);


  List<Community> selectCommunityList(long userNo);

  List<Comment> selectCommentList(long writingNo);

  List<Community> selectTag(String tag);

  List<Community> communityList(String searchValue);

  void deleteCommunity(long writingNo);

  Comment commentDelete(long commentNo);


  UserFile findByUserNo(long userNo);


  int addFavorite(long userNo, long commentNo);

  void removeUser(long userNo);


  int addCommunityFile(CommunityFileDTO communityFileDTO, Community community, User user);


  CommunityFile findByWritingNo(long writingNo);


  List<CommentFavorite> selectFavoriteList(long userNo, long writingNo);

  void modifyComment(long commentNo, String commentContent);


  int removeFavorite(long userNo, long commentNo);


  List<Payment> selectProduct(String userId);
}
