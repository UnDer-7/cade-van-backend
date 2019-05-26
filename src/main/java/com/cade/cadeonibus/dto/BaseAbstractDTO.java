package com.cade.cadeonibus.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseAbstractDTO implements Serializable {
  private static final long serialVersionUID = 7868841091728770710L;
  private Long id;
}
