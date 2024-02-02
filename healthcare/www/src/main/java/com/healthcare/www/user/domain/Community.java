package com.healthcare.www.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Community {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="writing_no")
  private long writingNo; // 글번호

  @Column
  private long userNo; // 유저번호

  @Column(name="writing_readCount")
  @ColumnDefault("0")
  private long writingReadCount; // 조회수

  @Column(name="writing_title")
  private String writingTitle; // 글제목

  @Column(name="writing_writer")
  private String writingWriter; // 작성자

  @Column(name="writing_content")
  private String writingContent; // 글내용

  @Column(name="writing_recommend")
  @ColumnDefault("0")
  private long writingRecommend; // 추천수

  @Column(name="writing_regDate")
  @UpdateTimestamp
  private LocalDateTime writingRegDate; // 작성일

  @Column(name="writing_tag")
  private String writingTag; // 태그



}
