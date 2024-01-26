package com.healthcare.www.plan.repository;

import com.healthcare.www.plan.domain.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
    List<ExerciseSet> findByPlanNo(long planNo);

    void deleteByPlanNo(long planNo);
}
