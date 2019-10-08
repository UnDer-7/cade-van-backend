package com.cade.cadeonibus.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
  public static Perfil fromValue(final String value) throws Exception {
    if (value.equalsIgnoreCase("driver")) {
      return Perfil.DRIVER;
    }

    if (value.equalsIgnoreCase("responsible")) {
      return Perfil.RESPONSIBLE;
    }

    throw new Exception("Perfil: " + value + " Nao encontrado");
  }

  public static Perfil toEnum(Integer cod) {
    if (cod == null) return null;

    for (Perfil value : Perfil.values()) {
      if (cod.equals(value.getCod())) return value;
    }

    throw new IllegalArgumentException("Código de perfil inválido " + cod);
  }
}
