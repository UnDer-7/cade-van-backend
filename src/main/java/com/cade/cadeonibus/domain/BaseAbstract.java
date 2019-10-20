package com.cade.cadeonibus.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
abstract class BaseAbstract implements Serializable {
  private static final long serialVersionUID = 6657939650552911228L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
