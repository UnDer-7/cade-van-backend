package com.cade.cadeonibus.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "itinerary")
public class Itinerary extends BaseAbstract {

  @OneToMany(mappedBy = "itinerary")
  List<ItineraryChild> itineraryChildList;
  @Column(name = "description")
  private String description;
}
