package com.cade.cadeonibus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "child")
public class Child extends BaseAbstract{

  @Column(name = "name")
  private String name;

  @Column(name = "school")
  private String school;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "period")
  private String period;

  @ManyToOne
  @JoinColumn(name = "responsible_id")
  private Responsible responsible;

  @ManyToOne()
  @JoinColumn(name = "driver_id")
  private Driver driver;
}
