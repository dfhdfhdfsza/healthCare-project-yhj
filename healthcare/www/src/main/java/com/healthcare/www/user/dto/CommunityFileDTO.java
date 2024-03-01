package com.healthcare.www.user.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityFileDTO {
  /* 커뮤니티 사진 */

  private String writingUUID;

  private long writingNo;

  private String writingFileName;

  private long writingFileSize;

  private String writingFileSaveDir;

  private String writingFileType;
}
