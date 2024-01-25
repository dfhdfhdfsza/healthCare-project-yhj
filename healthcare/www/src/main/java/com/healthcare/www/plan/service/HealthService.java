package com.healthcare.www.plan.service;

import com.healthcare.www.plan.domain.FullCalendarVO;
import com.healthcare.www.plan.domain.UserPlan;
import com.healthcare.www.plan.domain.planDTO;

import java.util.List;

public interface HealthService {
    void planSetting(planDTO pdto);

    List<FullCalendarVO> getEventList(Long userNo);


}
