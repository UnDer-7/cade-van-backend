package com.cade.cadeonibus.dto;

import lombok.Data;

@Data
public class ItineraryChildDTO extends BaseAbstractDTO {

  private Integer order;

  private ChildDTO child;

  private ItineraryDTO itinerary;
}
