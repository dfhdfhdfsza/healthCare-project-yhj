package com.healthcare.www.user.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFileDTO {
  private long userNo; 
  private String userUUID;
  private String userFileName;
  private long userFileSize;
  private String userFileSaveDir;
  private String userFileType; // 프로필사진인지  , 커뮤니티 사진인지
}
