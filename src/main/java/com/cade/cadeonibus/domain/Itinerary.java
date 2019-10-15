package com.cade.cadeonibus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "itinerary")
public class Itinerary extends BaseAbstract {

  @OneToMany(mappedBy = "itinerary")
  private List<ItineraryChild> itineraryChildList;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "driver_id")
  private Driver driver;
}
