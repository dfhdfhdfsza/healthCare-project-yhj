package com.healthcare.www.plan.repository;

import com.healthcare.www.plan.domain.UserPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {

    UserPlan findTopByOrderByUserPlanNoDesc();
}
