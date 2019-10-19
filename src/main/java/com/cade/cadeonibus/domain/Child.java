package com.cade.cadeonibus.domain;

import com.cade.cadeonibus.enums.ChildStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
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

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private ChildStatus status;

  @ManyToOne
  @JoinColumn(name = "responsible_id")
  private Responsible responsible;

  @ManyToOne()
  @JoinColumn(name = "driver_id")
  private Driver driver;
}
