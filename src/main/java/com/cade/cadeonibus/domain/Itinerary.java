package com.cade.cadeonibus.domain;

import com.cade.cadeonibus.enums.ItineraryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "itinerary")
public class Itinerary extends BaseAbstract {

  @Column(name = "description")
  private String description;

  @Column(name = "is_ativo")
  private boolean isAtivo;

  @Enumerated(EnumType.STRING)
  @Column(name = "itinerary_type")
  private ItineraryType type;

  @ManyToOne
  @JoinColumn(name = "driver_id")
  private Driver driver;
}
