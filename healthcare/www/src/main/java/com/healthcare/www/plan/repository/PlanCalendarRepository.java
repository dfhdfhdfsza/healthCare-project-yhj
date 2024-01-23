package com.healthcare.www.plan.repository;

import com.healthcare.www.plan.domain.PlanCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCalendarRepository extends JpaRepository<PlanCalendar, Long> {

    PlanCalendar findTopByOrderByPlanNoDesc();
}
