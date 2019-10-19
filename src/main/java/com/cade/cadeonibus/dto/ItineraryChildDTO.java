package com.cade.cadeonibus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ItineraryChildDTO extends BaseAbstractDTO {

  @NotNull
  private Integer childOrder;

  @NotNull
  private ChildDTO child;

  private ItineraryDTO itinerary;
}
