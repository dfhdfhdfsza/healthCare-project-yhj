package com.healthcare.www.user.service;

import com.healthcare.www.membership.domain.Membership;
import com.healthcare.www.membership.repository.MembershipRepository;
import com.healthcare.www.order.domain.Payment;
import com.healthcare.www.order.repository.PaymentRepository;
import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.repository.ProductRepository;
import com.healthcare.www.user.domain.*;
import com.healthcare.www.user.dto.*;
import com.healthcare.www.user.repository.*;
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
  private final CommentRepository commentRepository;
  private final CommentFavoriteRepository commentFavoriteRepository;
  private final CommunityFileRepository communityFileRepository;
  private final PaymentRepository paymentRepository;
  private final ProductRepository productRepository;

  private final MembershipRepository membershipRepository;
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

    // 멤버쉽 등록
    Membership membership = Membership.builder()
        .userId(user.getUserId())
        .point(0)
        .build();

    membershipRepository.save(membership);
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
    community.setWritingReadCount(community.getWritingReadCount()+1);
    communityRepository.save(community);
    return community;
  }

  @Override
  public Comment addComment(CommentDTO commentDTO) {
    Comment comment = Comment.builder()
        .commentContent(commentDTO.getCommentContent())
        .writingNo(commentDTO.getWritingNo())
        .commentWriter(commentDTO.getCommentWriter())
        .userNo(commentDTO.getUserNo())
        .build();

    commentRepository.save(comment);

    return comment;
  }

  @Override
  public List<Community> selectCommunityList(long userNo) {
    List<Community> list = communityRepository.findByUserNo(userNo);
    return list;
  }

  @Override
  public List<Comment> selectCommentList(long writingNo) {
    List<Comment> list = commentRepository.findByWritingNo(writingNo);
    return list;
  }

  @Override
  public List<Community> selectTag(String tag) {
    List<Community> list = communityRepository.findByWritingTag(tag);
    return list;
  }

  @Override
  public List<Community> communityList(String searchValue) {
    List<Community> list = communityRepository.findByWritingTitleLike("%"+searchValue+"%");

    return list;
  }

  @Override
  public void deleteCommunity(long writingNo) {
    // 게시글 삭제
    communityRepository.deleteById(writingNo);

  }

  @Override
  public Comment commentDelete(long commentNo) {
    // 댓글삭제
    Comment comment = commentRepository.findByCommentNo(commentNo);

    commentRepository.deleteById(commentNo);

    return comment;
  }

  @Override
  public UserFile findByUserNo(long userNo) {
    // 커뮤니티 프로필사진
    UserFile file = userFileRepository.findByUserNo(userNo);

    return file;
  }

  @Override
  public int addFavorite(long userNo, long commentNo) {

    CommentFavorite favorite = CommentFavorite.builder()
            .userNo(userNo)
            .commentNo(commentNo)
            .build();

    commentFavoriteRepository.save(favorite);

    Comment comment = commentRepository.findByCommentNo(commentNo);
    comment.setCommentFavorite(comment.getCommentFavorite()+1);

    commentRepository.save(comment);



    return 1;
  }

  @Override
  public void removeUser(long userNo) {
    // 커뮤니티, 댓글, 프로필사진, 개인정보 , 좋아요 기록 전부삭제

    // 회원 삭제
    userRepository.deleteById(userNo);
    // 회원 정보삭제
    userInfoRepository.deleteById(userNo);
    // 프로필사진 삭제
    userFileRepository.deleteById(userNo);
    // 작성글
    communityRepository.deleteByUserNo(userNo);
    // 작성글 -> 이미지
    communityFileRepository.deleteByUserNo(userNo);
    // 댓글
    commentRepository.deleteByUserNo(userNo);
    // 댓글 추천
    commentFavoriteRepository.deleteByUserNo(userNo);

  }

  @Override
  public int addCommunityFile(CommunityFileDTO communityFileDTO, Community community, User user) {
    CommunityFile file = CommunityFile.builder()
        .writingFileName(communityFileDTO.getWritingFileName())
        .writingFileSize(communityFileDTO.getWritingFileSize())
        .writingFileSaveDir(communityFileDTO.getWritingFileSaveDir())
        .writingUUID(communityFileDTO.getWritingUUID())
        .writingFileType(communityFileDTO.getWritingFileType())
        .writingNo(community.getWritingNo())
        .userNo(user.getUserNo())
        .build();


    communityFileRepository.save(file);
    return 1;
  }

  @Override
  public CommunityFile findByWritingNo(long writingNo) {
    CommunityFile file = communityFileRepository.findByWritingNo(writingNo);
    return file;
  }

  @Override
  public List<CommentFavorite> selectFavoriteList(long userNo, long writingNo) {
    List<CommentFavorite> list = commentFavoriteRepository.findByUserNo(userNo);
    // userNo = 현재 로그인한 사람 writingNo = 현재 게시글

    return list;
  }

  @Override
  public void modifyComment(long commentNo, String commentContent) {
    Comment comment = commentRepository.findByCommentNo(commentNo);
    comment.setCommentContent(commentContent);

    commentRepository.save(comment);
  }

  @Override
  public int removeFavorite(long userNo, long commentNo) {
    CommentFavorite commentFavorite = commentFavoriteRepository.findByUserNoAndCommentNo(userNo,commentNo);
    commentFavoriteRepository.deleteById(commentFavorite.getFavoriteNo());

    return 1;
  }

  @Override
  public List<Payment> selectProduct(String userId) {

    List<Payment> paymentList = paymentRepository.findAllByUserId(userId);





    return paymentList;
  }


}
