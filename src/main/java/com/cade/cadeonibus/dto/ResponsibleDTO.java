package com.cade.cadeonibus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ResponsibleDTO extends BaseAbstractDTO {
  private String name;
  private String email;
  private String password;
  private String phone;
}
