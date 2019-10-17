package com.cade.cadeonibus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItineraryChildDTO extends BaseAbstractDTO {

  private Integer order;

  private ChildDTO child;

  private ItineraryDTO itinerary;
}
