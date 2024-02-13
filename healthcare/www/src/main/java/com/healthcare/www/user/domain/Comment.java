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
public class Comment {


  @Column(name="writing_no")
  private long writingNo;

  @Id
  @Column(name="comment_no")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long commentNo;

  @Column(name="comment_writer")
  private String commentWriter;

  @Column(name="comment_content")
  private String commentContent;

  @Column(name="comment_regDate")
  @UpdateTimestamp
  private LocalDateTime commentRegDate;

  @Column(name="comment_favorite")
  @ColumnDefault("0")
  private long commentFavorite; // 추천수

  @Column(name="user_no")
  private long userNo; // 유저 번호

}
