package com.healthcare.www.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
  private long userNo; // 유저번호
  private double infoHeight; // 유저 키
  private double infoWeight; // 유저 몸무게
  private double infoSkeletal; // 유저 골격근량
  private double infoBody; // 유저 체지방률
  private double infoMetabolic; // 유저 기초대사량

}
