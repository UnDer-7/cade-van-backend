package com.cade.cadeonibus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"User\"")
public class User extends BaseAbstract {

  @Column(name = "login")
  private String login;

  @JsonIgnore
  @Column(name = "password_hash")
  private String passwordHash;

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

  @Override
  public String toString() {
    return "User{" +
      "login='" + login + '\'' +
      ", passwordHash='" + passwordHash + '\'' +
      '}';
  }
}
