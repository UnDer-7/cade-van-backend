package com.cade.cadeonibus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "DRIVER")
public class Driver extends BaseAbstract {
  @Column(name = "name")
  private String name;

  @Column(name = "phone")
  private String phone;

  @Column(name = "cpf")
  private String cpf;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}