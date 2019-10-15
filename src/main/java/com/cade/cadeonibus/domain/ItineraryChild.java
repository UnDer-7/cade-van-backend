package com.cade.cadeonibus.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "itinerary_child")
public class ItineraryChild extends BaseAbstract {

  @Column(name = "child_order")
  private Integer order;

  @OneToOne
  @JoinColumn(name = "child_id", referencedColumnName = "id")
  private Child child;

  @OneToOne
  @JoinColumn(name = "itinerary_id", referencedColumnName = "id")
  private Itinerary itinerary;

}
