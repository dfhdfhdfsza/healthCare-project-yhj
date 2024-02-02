package com.healthcare.www.user.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDTO {


  private long userNo; // 유저번호

  private String writingTitle; // 글제목

  private String writingContent; // 글내용

  private String writingTag; // 태그
  
  private String writingWriter; // 작성자
}
