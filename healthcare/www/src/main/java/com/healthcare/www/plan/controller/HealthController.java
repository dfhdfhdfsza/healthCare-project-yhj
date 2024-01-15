package com.healthcare.www.plan.controller;

import com.healthcare.www.plan.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/health/*")
@RequiredArgsConstructor
public class HealthController {

  private HealthService hsv;

  @GetMapping("/healthplan")
  public String moveHealthplan(){return "health/healthplan";}
}
