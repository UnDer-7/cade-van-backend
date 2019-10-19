package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.Perfil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserRegisterDTO {
  private String nickname;

  @CPF
  private String cpf;

  @NotEmpty
  @Email
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
