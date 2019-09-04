package com.cade.cadeonibus.dto;

public class UserRegisterDTO {
  private String login;
  private String password;
  private String name;
  private String phone;
  private String cpf;
  private String nickname;
  private String type;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "UserRegisterDTO{" +
      "login='" + login + '\'' +
      ", password='" + password + '\'' +
      ", name='" + name + '\'' +
      ", phone='" + phone + '\'' +
      ", cpf='" + cpf + '\'' +
      ", nickname='" + nickname + '\'' +
      ", type='" + type + '\'' +
      '}';
  }
}
