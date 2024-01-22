package com.healthcare.www.user.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User{
  /*
    columnDefinition = "varchar(2500) default" / type , default 값 설정
    nullable = false / not null
    @Lob 용랑 큰 문자열
    @ID = pk
  */

  @Id
  @Column(name = "user_no")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userNo; // 유저 고유 번호

  @Column(name = "user_id")
  private String userId; // 유저 아이디

  @Column(name = "user_password")
  private String userPassword; // 유저 패스워드

  @Column(name = "user_name")
  private String userName; // 유저명

  @Column(name = "user_address")
  private String userAddress; // 주소

  @Column(name = "user_number")
  private String userNumber; // 전화번호

  @Column(name = "user_age")
  private int userAge; // 나이

  @Column(name="user_mail")
  private String userMail; // 성별

  @Column(name="user_role")
  private String userRole;


}
