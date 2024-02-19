package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.CommunityFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommunityFileRepository extends JpaRepository<CommunityFile, Long> {

  CommunityFile findByWritingNo(long writingNo);

  @Transactional
  void deleteByUserNo(long userNo);
}
