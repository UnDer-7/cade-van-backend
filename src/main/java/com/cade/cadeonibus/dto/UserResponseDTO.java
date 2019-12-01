package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.Perfil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserResponseDTO implements Serializable {
  private static final long serialVersionUID = 5904748469840948998L;

  private Long userId;
  private Long responsibleId;
  private Long driverId;
  private String name;
  private String nickname;
  private String email;
  private String phone;
  private String cpf;
  private String code;
  private Perfil perfil;

  public UserResponseDTO(final ResponsibleDTO responsible) {
    this.userId = responsible.getUserId();
    this.responsibleId = responsible.getId();
    this.name = responsible.getName();
    this.email = responsible.getEmail();
    this.phone = responsible.getPhone();
    this.perfil = Perfil.RESPONSIBLE;
  }

  public UserResponseDTO(final DriverDTO driver) {
    this.userId = driver.getUserId();
    this.driverId = driver.getId();
    this.name = driver.getName();
    this.nickname = driver.getNickname();
    this.email = driver.getEmail();
    this.phone = driver.getPhone();
    this.cpf = driver.getCpf();
    this.code = driver.getCode();
    this.perfil = Perfil.DRIVER;
  }
}
