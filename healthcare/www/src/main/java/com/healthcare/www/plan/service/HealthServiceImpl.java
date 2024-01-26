package com.healthcare.www.plan.service;

import com.healthcare.www.plan.domain.*;
import com.healthcare.www.plan.repository.ExerciseSetRepository;
import com.healthcare.www.plan.repository.PlanCalendarRepository;
import com.healthcare.www.plan.repository.UserPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthServiceImpl implements HealthService{

    private final UserPlanRepository userPlanRepository;
    private final PlanCalendarRepository planCalendarRepository;
    private final ExerciseSetRepository exerciseSetRepository;

    @Override
    @Transactional
    public void planSetting(planDTO pdto) {


        //user_plan테이블 저장
        UserPlan userplan= UserPlan.builder().planDate(pdto.getPlanDate())
                .userNo(pdto.getUserNo()).build();
        log.info("userplan:"+userplan);
        userPlanRepository.save(userplan);


        //plan_calendar테이블 저장
        PlanCalendar planCalendar=PlanCalendar.builder().
                exerciseName(pdto.getExerciseName()).build();
        log.info("planCalendar"+planCalendar);
        planCalendarRepository.save(planCalendar);


        //마지막으로 추가한 userplan객체 불러오기
        UserPlan lastuserPlan = userPlanRepository.findTopByOrderByUserPlanNoDesc();
        //마지막으로 추가한 PlanCalendar 객체 불러오기
        long lastplanNo=planCalendarRepository.findTopByOrderByPlanNoDesc().getPlanNo();

        lastuserPlan.setPlanNo(lastplanNo); //PlanCalendar객체의 planNo세팅
        userPlanRepository.save(lastuserPlan);  //수정

        //exercise_set테이블 저장
        List<ExerciseSet> setlist=new ArrayList<>();
        for (int i=0;i<pdto.getSetVOList().size();i++){
            ExerciseSet exerciseSet=ExerciseSet.builder().planNo(lastplanNo)
                    .exerciseWeight(pdto.getSetVOList().get(i).getExerciseWeight())
                    .exerciseCount(pdto.getSetVOList().get(i).getExerciseCount())
                    .exerciseCheck(pdto.getSetVOList().get(i).isExerciseCheck()).build();
            setlist.add(exerciseSet);
        }
        log.info("setlist:"+setlist);
        exerciseSetRepository.saveAll(setlist);

    }

    @Override
    public List<FullCalendarVO> getEventList(Long userNo) {

        List<FullCalendarVO> FullCalendarList=new ArrayList<>();

        List<UserPlan>userPlanList=userPlanRepository.findByUserNo(userNo);
        log.info("유저플랜리스트:"+userPlanList);

        //FullCalendarList 만들기
        for (int i=0;i<userPlanList.size();i++){
            FullCalendarVO fcvo=new FullCalendarVO();

            //FullCalendarVO value들 불러오기
            UserPlan userPlan=userPlanList.get(i);
            PlanCalendar pc=planCalendarRepository.findByPlanNo(userPlan.getPlanNo());
            List<ExerciseSet>exerciseSetList=exerciseSetRepository.findByPlanNo(userPlan.getPlanNo());

            //FullCalendarVO에 세팅
            fcvo.setTitle(pc.getExerciseName());
            fcvo.setStart(userPlan.getPlanDate());
            fcvo.setUserPlan(userPlanList.get(i));
            fcvo.setPlanCalendar(pc);
            fcvo.setExerciseSetList(exerciseSetList);

            FullCalendarList.add(fcvo);
        }

        return FullCalendarList;
    }

    @Override
    @Transactional
    public void delPlan(Long userPlanNo)
    {
        //user plan에 있는 planNo가 필요하므로 user plan을 불러온다
        UserPlan userPlan=userPlanRepository.findByUserPlanNo(userPlanNo);
        log.info("userPlan:"+userPlan);
        //plan calendar 삭제
        planCalendarRepository.deleteByPlanNo(userPlan.getPlanNo());

        //exercise set 삭제
        exerciseSetRepository.deleteByPlanNo(userPlan.getPlanNo());

        //user plan 삭제
        userPlanRepository.deleteByUserPlanNo(userPlanNo);
    }
}
