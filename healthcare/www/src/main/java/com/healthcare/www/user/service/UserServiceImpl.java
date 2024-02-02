package com.healthcare.www.user.service;

import com.healthcare.www.user.domain.Community;
import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.domain.UserFile;
import com.healthcare.www.user.domain.UserInfo;
import com.healthcare.www.user.dto.*;
import com.healthcare.www.user.repository.CommunityRepository;
import com.healthcare.www.user.repository.UserFileRepository;
import com.healthcare.www.user.repository.UserInfoRepository;
import com.healthcare.www.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final UserInfoRepository userInfoRepository;
  private final UserFileRepository userFileRepository;
  private final CommunityRepository communityRepository;

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
    info.setInfoSkeletal(userInfo.getInfoSkeletal());
    userInfoRepository.save(info);

  }

  @Override
  public UserInfo selectUserInfo(long userNo) {
    UserInfo info = userInfoRepository.findByUserNo(userNo);
    return info;
  }

  @Override
  public UserInfo putUserInfo(UserInfoDTO userInfoDTO) {

    UserInfo info = UserInfo.builder().
        userNo(userInfoDTO.getUserNo()).
        infoHeight(userInfoDTO.getInfoHeight()).
        infoWeight(userInfoDTO.getInfoWeight()).
        infoBody(userInfoDTO.getInfoBody()).
        infoSkeletal(userInfoDTO.getInfoSkeletal()).
        infoMetabolic(userInfoDTO.getInfoMetabolic()).
        build();

    userInfoRepository.save(info);
    return info;
  }

  @Override
  public int addImage(UserFileDTO userFileDTOS, User user) {
    UserFile userFile = UserFile.builder()
        .userFileName(userFileDTOS.getUserFileName())
        .userFileSize(userFileDTOS.getUserFileSize())
        .userFileSaveDir(userFileDTOS.getUserFileSaveDir())
        .userNo(user.getUserNo())
        .userUUID(userFileDTOS.getUserUUID())
        .userFileType(userFileDTOS.getUserFileType())
        .build();

    userFileRepository.save(userFile);

    return 0;
  }

  @Override
  public UserFile selectUserFile(long userNo) {
    UserFile file = userFileRepository.findByUserNo(userNo);

    return file;
  }

  @Override
  public Community addCommunity(CommunityDTO communityDTO) {

    Community community = Community.builder()
        .writingContent(communityDTO.getWritingContent())
        .writingTitle(communityDTO.getWritingTitle())
        .writingTag(communityDTO.getWritingTag())
        .userNo(communityDTO.getUserNo())
        .writingWriter(communityDTO.getWritingWriter())
        .build();

    communityRepository.save(community);

    return community;
  }

  @Override
  public List<Community> selectAll() {
    List<Community> communityList = communityRepository.findAll();

    return communityList;
  }

  @Override
  public Community selectCommunity(long writingNo) {
    Community community = communityRepository.findByWritingNo(writingNo);

    return community;
  }


}
