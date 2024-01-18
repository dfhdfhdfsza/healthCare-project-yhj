package com.healthcare.www.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {

  @NotBlank(message = "아이디를 입력해주세요")
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "아이디는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
  private String userId; // id

  @NotBlank(message = "비밀번호를 입력해주세요")
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
  private String userPassword; // pwd

  @NotBlank(message = "이름을 입력해주세요")
  private String userName; // 이름

  @NotEmpty(message = "주소를 입력해주세요")
  private String userAddress; // 주소

  @NotBlank(message = "전화번호를 입력해주세요")
  private String userNumber; // 전화번호

  private int userAge; // 나이

  private String userMail; // 성별
  

}
