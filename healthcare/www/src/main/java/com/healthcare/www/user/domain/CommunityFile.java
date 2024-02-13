package com.healthcare.www.user.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommunityFile {

  /* 커뮤니티 사진 */
  @Column(name = "writing_uuid")
  private String writingUUID;

  @Id
  @Column(name = "writing_no")
  private long writingNo;

  @Column(name="user_no")
  private long userNo;

  @Column(name = "writing_fileName")
  private String writingFileName;

  @Column(name = "writing_fileSize")
  private long writingFileSize;

  @Column(name = "writing_fileSaveDir")
  private String writingFileSaveDir;

  @Column(name = "writing_fileType")
  private String writingFileType;
}
