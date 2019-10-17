package com.cade.cadeonibus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDTO {
  @JsonProperty("lat")
  private double lat;

  @JsonProperty("lon")
  private double lon;

  @JsonProperty("driverId")
  private Long driverId;
}
