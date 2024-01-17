package com.healthcare.www.plan.controller;

import com.healthcare.www.plan.service.HealthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
@RequestMapping("/health/*")
@RequiredArgsConstructor
@Slf4j
public class HealthController {

  private final HealthService hsv;



  @GetMapping("/healthplan")
  public String moveHealthplan(){
    log.info("ㅋㅋ");
    return "health/healthplan";
  }


}
