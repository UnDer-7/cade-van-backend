package com.cade.cadeonibus.dto;

import lombok.Data;

@Data
public class ItineraryChildDTO extends BaseAbstractDTO {

  private Integer order;

  private Long childId;

  private String childName;

  private Long itineraryId;

}
