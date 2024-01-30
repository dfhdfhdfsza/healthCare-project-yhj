package com.healthcare.www.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class UserFile {
  @Id
  @Column(name = "user_uuid")
  private String userUUID;

  @Column(name = "user_no")
  private long userNo;

  @Column(name = "user_fileName")
  private String userFileName;

  @Column(name = "user_fileSize")
  private long userFileSize;

  @Column(name = "user_fileSaveDir")
  private String userFileSaveDir;

  @Column(name = "user_fileType")
  private String userFileType;
}
