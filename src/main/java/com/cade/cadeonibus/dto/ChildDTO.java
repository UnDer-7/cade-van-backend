package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.domain.BaseAbstract;
import com.cade.cadeonibus.domain.Responsible;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ChildDTO extends BaseAbstract {
  private String name;
  private String school;
  private Integer age;
  private String period;
  private Long responsibleId;
  private Long driverId;
}
