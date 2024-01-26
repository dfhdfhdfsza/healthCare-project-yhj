package com.healthcare.www.plan.repository;

import com.healthcare.www.plan.domain.UserPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {

    UserPlan findTopByOrderByUserPlanNoDesc();

    List<UserPlan> findByUserNo(Long userNo);

    UserPlan findByUserPlanNo(Long userPlanNo);

    void deleteByUserPlanNo(Long userPlanNo);
}
