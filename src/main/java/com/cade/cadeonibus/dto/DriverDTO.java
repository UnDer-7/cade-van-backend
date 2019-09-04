package com.cade.cadeonibus.dto;

import lombok.Data;

@Data
public class DriverDTO {
  private String name;

  private String phone;

  private String cpf;

  private Long userId;

  public DriverDTO() {
  }

  public DriverDTO(UserRegisterDTO userRegisterDTO, Long userId) {
    this.name = userRegisterDTO.getName();
    this.phone = userRegisterDTO.getPhone();
    this.cpf = userRegisterDTO.getCpf();
    this.userId = userId;
  }
}
