package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.enums.Perfil;
import lombok.Data;

@Data
public class UserResponseDTO {
  private Long id;
  private String name;
  private String nickname;
  private String email;
  private String phone;
  private String cpf;
  private String code;
  private Perfil perfil;

  public UserResponseDTO(final Responsible responsible) {
    this.id = responsible.getId();
    this.name = responsible.getName();
    this.email = responsible.getEmail();
    this.phone = responsible.getPhone();
    this.perfil = Perfil.RESPONSIBLE;
  }

  public UserResponseDTO(final ResponsibleDTO responsible) {
    this.id = responsible.getId();
    this.name = responsible.getName();
    this.email = responsible.getEmail();
    this.phone = responsible.getPhone();
    this.perfil = Perfil.RESPONSIBLE;
  }

  public UserResponseDTO(final Driver driver) {
    this.id = driver.getId();
    this.name = driver.getName();
    this.nickname = driver.getNickname();
    this.email = driver.getEmail();
    this.phone = driver.getPhone();
    this.cpf = driver.getCpf();
    this.code = driver.getCode();
    this.perfil = Perfil.DRIVER;
  }

  public UserResponseDTO(final DriverDTO driver) {
    this.id = driver.getId();
    this.name = driver.getName();
    this.nickname = driver.getNickname();
    this.email = driver.getEmail();
    this.phone = driver.getPhone();
    this.cpf = driver.getCpf();
    this.code = driver.getCode();
    this.perfil = Perfil.DRIVER;
  }
}
