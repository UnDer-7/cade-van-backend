package com.cade.cadeonibus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ResponsibleDTO extends BaseAbstractDTO {
  private String name;
  private String email;
  private String phone;
  private Long userId;

  public ResponsibleDTO() {
  }

  public ResponsibleDTO(UserRegisterDTO userRegisterDTO, Long userId) {
    this.name = userRegisterDTO.getName();
    this.email = userRegisterDTO.getEmail();
    this.phone = userRegisterDTO.getPhone();
    this.userId = userId;
  }
}
