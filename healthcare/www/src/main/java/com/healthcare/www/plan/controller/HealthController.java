package com.healthcare.www.plan.controller;

import com.healthcare.www.plan.domain.FullCalendarDTO;
import com.healthcare.www.plan.domain.HealthInfo;
import com.healthcare.www.plan.domain.UserPlan;
import com.healthcare.www.plan.domain.planDTO;
import com.healthcare.www.plan.service.HealthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/health/*")
@RequiredArgsConstructor
@Slf4j
public class HealthController {

  private final HealthService hsv;

  int isOk;

  @GetMapping("/healthplan")
  public String moveHealthplan(){
    return "health/healthplan";
  }

  @PostMapping("/planSetting")
  public String planSetting(@RequestBody planDTO pdto)
  {
    log.info("pdto:"+pdto);
    hsv.planSetting(pdto);
    return "redirect:/health/healthplan";
  }
  @GetMapping("/getEventList")
  public ResponseEntity<List<FullCalendarDTO>> getEventList(@RequestParam("userNo")Long userNo)
  {
    List<FullCalendarDTO> eventlist=hsv.getEventList(userNo);

    return new ResponseEntity<List<FullCalendarDTO>>(eventlist, HttpStatus.OK);

  }
  @GetMapping("/delPlan")
  public String delPlan(@RequestParam("userPlanNo")Long userPlanNo)
  {
    log.info("userPlanNo:"+userPlanNo);
    hsv.delPlan(userPlanNo);
    return "redirect:/health/healthplan";
  }

  @PostMapping("/modPlan")
  public ResponseEntity<String> modPlan(@RequestBody FullCalendarDTO fcdto)
  {
    isOk=hsv.modPlan(fcdto);
    return isOk>0? new ResponseEntity<>("1",HttpStatus.OK):new ResponseEntity<>("0",HttpStatus.INTERNAL_SERVER_ERROR);
  }
  @PostMapping("/modPlanDate")
  public  ResponseEntity<String>modPlanDate(@RequestBody UserPlan userPlan)
  {
    log.info("userPlan::"+userPlan);
    isOk=hsv.modPlanDate(userPlan);
    return isOk>0? new ResponseEntity<>("1",HttpStatus.OK):new ResponseEntity<>("0",HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/healthInfoList")
  public String moveHealthInfoList(){return "health/healthinfo";}

  @GetMapping("/getExerciseInfo")
  public ResponseEntity<Page<HealthInfo>>getExerciseInfo(@RequestParam("equipment")String equipment,@RequestParam("bodypart")String bodypart,@RequestParam("page")int page,@RequestParam("size")int size,@RequestParam("keyword")String keyword)
  {
    log.info("bodypart::"+bodypart);
    log.info("equipment::"+equipment);
    log.info("keyword::"+keyword);

    Page<HealthInfo> healthInfos=hsv.getExerciseInfo(equipment,bodypart,page,size,keyword);

    return new ResponseEntity<Page<HealthInfo>>(healthInfos, HttpStatus.OK);
  }
  @GetMapping("/getOneExerciseInfo")
  public ResponseEntity<HealthInfo> getOneExerciseInfo(@RequestParam("name")String name)
  {
    HealthInfo healthInfo=hsv.getOneExerciseInfo(name);

    return  new ResponseEntity<HealthInfo>(healthInfo,HttpStatus.OK);
  }

}
