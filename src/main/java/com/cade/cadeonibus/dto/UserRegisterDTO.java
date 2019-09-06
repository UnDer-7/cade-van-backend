package com.cade.cadeonibus.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRegisterDTO {
  @NotEmpty
  private String email;
  @NotEmpty
  private String password;
  @NotEmpty
  private String name;
  @NotEmpty
  private String phone;
  private String cpf;
  private String nickname;
  @NotEmpty
  private String type;
}
