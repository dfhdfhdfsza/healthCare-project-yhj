package com.healthcare.www.user.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFileDTO {

  private String userUUID;
  private String userFileName;
  private long userFileSize;
  private String userFileSaveDir;
  private String userFileType;
}
