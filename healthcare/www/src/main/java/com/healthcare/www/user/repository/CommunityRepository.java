package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {

  Community findByWritingNo(long writingNo);

  List<Community> findByUserNo(long userNo);

  List<Community> findByWritingTag(String tag);

  List<Community> findByWritingTitleLike(String searchValue);


  @Transactional
  void deleteByUserNo(long userNo);
}
