package com.healthcare.www.user.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {


  private String id;
  private String pwd;
}
