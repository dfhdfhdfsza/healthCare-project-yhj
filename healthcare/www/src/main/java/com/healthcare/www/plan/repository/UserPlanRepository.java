package com.healthcare.www.plan.repository;

import com.healthcare.www.plan.domain.UserPlanVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<UserPlanVO, Long> {

}
