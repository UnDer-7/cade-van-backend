package com.cade.cadeonibus.dto;

import lombok.Data;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
public class DriverDTO {
  private Long id;
  private String name;
  private String nickname;
  private String email;
  private String phone;
  private String cpf;
  private Long userId;
  private String code;

  public DriverDTO() {
  }

  public DriverDTO(UserRegisterDTO userRegisterDTO, Long userId) throws NoSuchAlgorithmException {
    this.name = userRegisterDTO.getName();
    this.nickname = userRegisterDTO.getNickname();
    this.email = userRegisterDTO.getEmail();
    this.phone = userRegisterDTO.getPhone();
    this.cpf = userRegisterDTO.getCpf();
    this.userId = userId;

    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(userId.toString().getBytes());
    this.code = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
  }
}
