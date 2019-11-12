package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.Perfil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserDTO extends BaseAbstractDTO {

  private String login;
  private String passwordHash;
  private Set<Perfil> perfis = new HashSet<>();
  private String deviceToken;

  public UserDTO(UserRegisterDTO userRegisterDTO, String passwordHash) {
    this.login = userRegisterDTO.getEmail();
    this.passwordHash = passwordHash;

    if (userRegisterDTO.getType() == Perfil.DRIVER) {
      setPerfis(new HashSet<>(EnumSet.of(Perfil.DRIVER)));
    } else if (userRegisterDTO.getType() == Perfil.RESPONSIBLE) {
      setPerfis(new HashSet<>(EnumSet.of(Perfil.RESPONSIBLE)));
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum perfil encontrado");
    }
  }

  @Override
  public String toString() {
    return "UserDTO{" +
      "login='" + login + '\'' +
      ", passwordHash='" + passwordHash + '\'' +
      ", perfis=" + perfis +
      ", deviceToken=" + deviceToken +
      '}';
  }
}
