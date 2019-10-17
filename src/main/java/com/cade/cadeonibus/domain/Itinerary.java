package com.cade.cadeonibus.domain;

import com.cade.cadeonibus.enums.ItineraryType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "itinerary")
public class Itinerary extends BaseAbstract {

  @Column(name = "description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "itinerary_type")
  private ItineraryType type;

  @ManyToOne
  @JoinColumn(name = "driver_id")
  private Driver driver;
}
