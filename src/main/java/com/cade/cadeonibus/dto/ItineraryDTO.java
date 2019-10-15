package com.cade.cadeonibus.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItineraryDTO {
  private List<ItineraryChildDTO> itineraryChildList;
  private String description;
  private Long driverId;
}
