package com.cade.cadeonibus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "DRIVER")
public class Driver extends PersonAbstract {
  @Column(name = "nickname")
  private String nickname;

  @Column(name = "cpf")
  private String cpf;
}
