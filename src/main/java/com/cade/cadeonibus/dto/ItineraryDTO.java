package com.cade.cadeonibus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItineraryDTO extends BaseAbstractDTO {
  private List<ItineraryChildDTO> itineraryChildren;
  private String description;
  private Long driverId;
}
