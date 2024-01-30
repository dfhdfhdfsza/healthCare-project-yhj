package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {
  UserFile findByUserNo(long userNo);
}
