package com.healthcare.www.plan.service;

import com.healthcare.www.plan.domain.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HealthService {
    void planSetting(planDTO pdto);

    List<FullCalendarDTO> getEventList(Long userNo);


    void delPlan(Long userPlanNo);

    int modPlan(FullCalendarDTO fcdto);

    int modPlanDate(UserPlan userPlan);

    void saveHealthInfo(HealthInfo healthInfo);

    void resetHealthInfo();

    Page<HealthInfo> getExerciseInfo(String equipment,String bodypart,int page, int size,String keyword);

    HealthInfo getOneExerciseInfo(String name);


}
