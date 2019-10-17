package com.cade.cadeonibus.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItineraryDTO extends BaseAbstractDTO {
  private List<ItineraryChildDTO> itineraryChildList;
  private String description;
  private Long driverId;
}
