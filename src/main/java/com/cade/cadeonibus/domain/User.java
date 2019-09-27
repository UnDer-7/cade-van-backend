package com.cade.cadeonibus.domain;

import com.cade.cadeonibus.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "\"User\"")
public class User extends BaseAbstract {

  @Column(name = "login")
  private String login;

  @JsonIgnore
  @Column(name = "password_hash")
  private String passwordHash;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "perfis")
  private Set<Perfil> perfis = new HashSet<>();
}
