package com.cade.cadeonibus.dto;

public class UserDTO extends BaseAbstractDTO {

  private String login;
  private String passwordHash;

  public UserDTO() {
  }

  public UserDTO(UserRegisterDTO userRegisterDTO, String passwordHash) {
    this.login = userRegisterDTO.getLogin();
    this.passwordHash = passwordHash;
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

  @Override
  public String toString() {
    return "User{" +
      "login='" + login + '\'' +
      ", passwordHash='" + passwordHash + '\'' +
      '}';
  }
}
