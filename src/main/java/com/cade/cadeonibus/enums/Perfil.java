package com.cade.cadeonibus.enums;

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

  public static Perfil toEnum(Integer cod) {
    if (cod == null) return null;

    for (Perfil value : Perfil.values()) {
      if (cod.equals(value.getCod())) return value;
    }

    throw new IllegalArgumentException("Código de perfil inválido " + cod);
  }
}
