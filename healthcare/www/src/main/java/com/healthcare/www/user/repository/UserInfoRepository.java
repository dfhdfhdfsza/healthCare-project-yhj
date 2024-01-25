package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
  UserInfo findByUserNo(long userNo);
}
