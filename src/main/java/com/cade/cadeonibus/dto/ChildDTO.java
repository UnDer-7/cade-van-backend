package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.ChildStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
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

  private ChildStatus status;

  private ResponsibleDTO responsible;

  private String driverCode;

  private Long driverId;
}
