package com.cade.cadeonibus.domain;

import com.cade.cadeonibus.enums.ChildStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "child")
public class Child extends BaseAbstract {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "school", nullable = false)
  private String school;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "period", nullable = false)
  private String period;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private ChildStatus status;

  @ManyToOne
  @JoinColumn(name = "responsible_id", nullable = false)
  private Responsible responsible;

  @ManyToOne
  @JoinColumn(name = "driver_id", nullable = false)
  private Driver driver;
}
