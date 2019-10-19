package com.cade.cadeonibus.domain;

import com.cade.cadeonibus.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
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
