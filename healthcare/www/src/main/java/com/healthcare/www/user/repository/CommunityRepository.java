package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {

  Community findByWritingNo(long writingNo);
}
