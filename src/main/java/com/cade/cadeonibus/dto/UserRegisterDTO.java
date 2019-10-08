package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.Perfil;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterDTO {
  private String nickname;

  @CPF
  private String cpf;

  @NotEmpty
  private String email;

  @NotEmpty
  private String password;

  @NotEmpty
  private String name;

  @NotEmpty
  private String phone;

  @NotNull
  private Perfil type;
}
