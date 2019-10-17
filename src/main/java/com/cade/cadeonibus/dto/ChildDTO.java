package com.cade.cadeonibus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ChildDTO extends BaseAbstractDTO {

  private Long id;

  @NotEmpty
  private String name;

  @NotEmpty
  private String school;

  @NotEmpty
  private LocalDate birthDate;

  @NotEmpty
  private String period;

  private Long responsibleId;

  private String driverCode;

  private Long driverId;
}
