package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.dto.dao.DriverDAO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
@NoArgsConstructor
public class DriverDTO {
  private Long id;
  private String name;
  private String nickname;
  private String email;
  private String phone;
  private String cpf;
  private Long userId;
  private String code;

  public DriverDTO(UserRegisterDTO userRegisterDTO, Long userId) {
    this.name = userRegisterDTO.getName();
    this.nickname = userRegisterDTO.getNickname();
    this.email = userRegisterDTO.getEmail();
    this.phone = userRegisterDTO.getPhone();
    this.cpf = userRegisterDTO.getCpf();
    this.userId = userId;

    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Nao possivel criar o MD5 do motorista");
    }

    md.update(userId.toString().getBytes());
    this.code = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
  }

  public DriverDTO(final DriverDAO dao) {
    this.id = dao.getId();
    this.name = dao.getName();
    this.nickname = dao.getNickname();
    this.email = dao.getEmail();
    this.phone = dao.getPhone();
    this.cpf = dao.getCpf();
    this.code = dao.getCode();
  }
}
