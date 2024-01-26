package com.healthcare.www.plan.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FullCalendarVO {
    private String title;   //제목(운동명)
    private String start;   //시작날짜

    private UserPlan userPlan;
    private PlanCalendar planCalendar;
    private List<ExerciseSet> exerciseSetList;    //세트정보 list
}
