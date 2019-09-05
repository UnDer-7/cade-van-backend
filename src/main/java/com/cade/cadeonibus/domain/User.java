package com.cade.cadeonibus.domain;

import com.cade.cadeonibus.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public Set<Perfil> getPerfis() {
    return perfis;
  }

  public void setPerfis(Set<Perfil> perfis) {
    this.perfis = perfis;
  }

  @Override
  public String toString() {
    return "User{" +
      "login='" + login + '\'' +
      ", passwordHash='" + passwordHash + '\'' +
      '}';
  }
}
