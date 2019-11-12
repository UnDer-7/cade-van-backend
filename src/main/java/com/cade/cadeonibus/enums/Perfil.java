package com.cade.cadeonibus.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
public enum Perfil {
  DRIVER(1, "Driver"),
  RESPONSIBLE(2, "Responsible");

  @Getter
  @Setter
  private int cod;

  @Getter
  @Setter
  private String descricao;

  @JsonCreator
  public static Perfil fromValue(final String value) {
    if (value.equalsIgnoreCase("driver")) {
      return Perfil.DRIVER;
    }

    if (value.equalsIgnoreCase("responsible")) {
      return Perfil.RESPONSIBLE;
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Perfil: " + value + " Nao encontrado");
  }

  public static Perfil toEnum(Integer cod) {
    if (cod == null) return null;

    for (Perfil value : Perfil.values()) {
      if (cod.equals(value.getCod())) return value;
    }

    throw new IllegalArgumentException("Código de perfil inválido " + cod);

  }
}
