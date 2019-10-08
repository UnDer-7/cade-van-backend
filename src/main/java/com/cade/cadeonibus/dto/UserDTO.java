package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.Perfil;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class UserDTO extends BaseAbstractDTO {

  private String login;
  private String passwordHash;
  private Set<Perfil> perfis = new HashSet<>();

  public UserDTO() {
  }

  public UserDTO(UserRegisterDTO userRegisterDTO, String passwordHash) throws Exception {
    this.login = userRegisterDTO.getEmail();
    this.passwordHash = passwordHash;

    if (userRegisterDTO.getType() == Perfil.DRIVER) {
      setPerfis(new HashSet<>(EnumSet.of(Perfil.DRIVER)));
    } else if (userRegisterDTO.getType() == Perfil.RESPONSIBLE) {
      setPerfis(new HashSet<>(EnumSet.of(Perfil.RESPONSIBLE)));
    } else {
      throw new Exception("Nenhum perfil encontrado");
    }
  }

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
    return "UserDTO{" +
      "login='" + login + '\'' +
      ", passwordHash='" + passwordHash + '\'' +
      ", perfis=" + perfis +
      '}';
  }
}
