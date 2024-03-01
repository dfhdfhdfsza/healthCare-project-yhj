package com.healthcare.www.user.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class UserInfo {

  @Id
  @Column(name = "user_no")
  private long userNo; // 유저 고유 번호

  @Column(name="info_height")
  private double infoHeight; // 유저 키

  @Column(name="info_weight")
  private double infoWeight; // 유저 몸무게

  @Column(name="info_skeletal")
  private double infoSkeletal; // 유저 골격근량

  @Column(name="info_body")
  private double infoBody; // 유저 체지방률

  @Column(name="info_metabolic")
  private double infoMetabolic; // 유저 기초대사량

}
