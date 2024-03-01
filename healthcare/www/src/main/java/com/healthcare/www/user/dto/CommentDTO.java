package com.healthcare.www.user.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

  private long writingNo; // 글번호

  private String commentWriter; // 작성자

  private String commentContent; // 댓글내용

  private long userNo; // 유저번호
}
