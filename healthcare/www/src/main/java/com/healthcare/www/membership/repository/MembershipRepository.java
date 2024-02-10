package com.healthcare.www.membership.repository;

import com.healthcare.www.membership.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MembershipRepository extends JpaRepository<Membership, String> {

    @Transactional
    @Modifying
    @Query("update Membership m set m.point = m.point + ?2 where m.userId = ?1")
    void updateUserIdByPoint(String userId, Integer point);

    @Transactional
    @Modifying
    @Query("update Membership m set m.point = m.point - ?1 where m.userId = ?2")
    void updatePointByUserId(Integer point, String userId);
}
