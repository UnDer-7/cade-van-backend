package com.cade.cadeonibus.enums;

public enum Perfil {
  DRIVER(1, "Driver"),
  RESPONSIBLE(2, "RESPONSIBLE");

  private int cod;
  private String descricao;

  Perfil(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public int getCod() {
    return cod;
  }

  public void setCod(int cod) {
    this.cod = cod;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public static Perfil toEnum(Integer cod) {
    if (cod == null) return null;

    for (Perfil value : Perfil.values()) {
      if (cod.equals(value.getCod())) return value;
    }

    throw new IllegalArgumentException("Código de perfil inválido " + cod);
  }
}
