package com.cade.cadeonibus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "driver")
public class Driver extends BaseAbstract {
  @Column(name = "name")
  private String name;

  @Column(name = "nickname", nullable = false)
  private String nickname;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "cpf", nullable = false)
  private String cpf;

  @Column(name = "code", nullable = false)
  private String code;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;
}
