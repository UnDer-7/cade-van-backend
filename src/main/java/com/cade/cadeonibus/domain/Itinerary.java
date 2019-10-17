package com.cade.cadeonibus.domain;

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

  @ManyToOne
  @JoinColumn(name = "driver_id")
  private Driver driver;
}
