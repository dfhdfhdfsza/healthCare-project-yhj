package com.healthcare.www.plan.controller;

import com.healthcare.www.plan.domain.FullCalendarVO;
import com.healthcare.www.plan.domain.UserPlan;
import com.healthcare.www.plan.domain.planDTO;
import com.healthcare.www.plan.service.HealthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@Controller
@RequestMapping("/health/*")
@RequiredArgsConstructor
@Slf4j
public class HealthController {

  private final HealthService hsv;



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
  public ResponseEntity<List<FullCalendarVO>> getEventList(@RequestParam("userNo")Long userNo)
  {
    List<FullCalendarVO> eventlist=hsv.getEventList(userNo);

    return new ResponseEntity<List<FullCalendarVO>>(eventlist, HttpStatus.OK);

  }
  @GetMapping("/delPlan")
  public String delPlan(@RequestParam("userPlanNo")Long userPlanNo)
  {
    log.info("userPlanNo:"+userPlanNo);
    hsv.delPlan(userPlanNo);
    return "redirect:/health/healthplan";
  }

}
