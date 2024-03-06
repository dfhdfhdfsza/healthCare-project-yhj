package com.healthcare.www.plan.service;

import com.healthcare.www.plan.domain.*;
import com.healthcare.www.plan.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private  final HealthInfoRepository healthInfoRepository;

    int isOk;

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
    public List<FullCalendarDTO> getEventList(Long userNo) {

        List<FullCalendarDTO> FullCalendarList=new ArrayList<>();

        List<UserPlan>userPlanList=userPlanRepository.findByUserNo(userNo);
        log.info("유저플랜리스트:"+userPlanList);

        //FullCalendarList 만들기
        for (int i=0;i<userPlanList.size();i++){
            FullCalendarDTO fcvo=new FullCalendarDTO();

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

    @Override
    public int modPlan(FullCalendarDTO fcdto)
    {
        try {
            // PlanCalendar 저장
            planCalendarRepository.save(fcdto.getPlanCalendar());

            // ExerciseSet 저장
            List<ExerciseSet> setlist = fcdto.getExerciseSetList();

            for (ExerciseSet exerciseSet : setlist) {
                exerciseSetRepository.save(exerciseSet);
            }
            return 1;   // 모든 저장 작업이 성공하면 1 반환
        } catch (Exception e) {
            e.printStackTrace();
            return 0;   // 저장 작업 중 실패한 경우 0 반환
        }
    }

    @Override
    public int modPlanDate(UserPlan userPlan)
    {
        try {
            userPlanRepository.save(userPlan);
            return 1;   // 모든 저장 작업이 성공하면 1 반환
        }catch (Exception e){
            e.printStackTrace();
            return 0;  // 저장 작업 중 실패한 경우 0 반환
        }
    }

    @Override
    public void saveHealthInfo(HealthInfo healthInfo) {
        healthInfoRepository.save(healthInfo);
    }

    @Override
    public void resetHealthInfo() {
        healthInfoRepository.resetHealthInfo();
    }

    @Override
    public Page<HealthInfo> getExerciseInfo(String equipment,String bodypart,int page, int size,String keyword)
    {
        PageRequest pageable = PageRequest.of(page, size);
        if(!keyword.isEmpty()){
            return healthInfoRepository.findByNameContaining(keyword,pageable);
        }
        else if(equipment.isEmpty()) //bodypart값만 들어왔을때
        {
            if(bodypart.equals("all")){
                return healthInfoRepository.findAll(pageable);
            }
            return healthInfoRepository.findByTarget(bodypart, pageable);
        }
        //equipment값만 들어왔을때
        return healthInfoRepository.findByEquipment(equipment,pageable);
    }

    @Override
    public HealthInfo getOneExerciseInfo(String name) {

        return healthInfoRepository.findByName(name);
    }




}
