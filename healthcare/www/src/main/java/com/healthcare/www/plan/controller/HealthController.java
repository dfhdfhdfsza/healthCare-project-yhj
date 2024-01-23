package com.healthcare.www.plan.controller;

import com.healthcare.www.plan.domain.planDTO;
import com.healthcare.www.plan.service.HealthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


}
