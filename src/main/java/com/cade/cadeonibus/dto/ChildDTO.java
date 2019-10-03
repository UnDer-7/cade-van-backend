package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.domain.BaseAbstract;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=true)
public class ChildDTO extends BaseAbstract {

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
