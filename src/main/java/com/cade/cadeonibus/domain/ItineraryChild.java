package com.cade.cadeonibus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "itinerary_child")
public class ItineraryChild extends BaseAbstract {

  @Column(name = "child_order")
  private Integer childOrder;

  @OneToOne
  @JoinColumn(name = "child_id", referencedColumnName = "id")
  private Child child;

  @OneToOne
  @JoinColumn(name = "itinerary_id", referencedColumnName = "id")
  private Itinerary itinerary;

}
