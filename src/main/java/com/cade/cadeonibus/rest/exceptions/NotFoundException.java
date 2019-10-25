package com.cade.cadeonibus.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
  public static final String DEFAULT_MESSAGE = "Recurso não encontrado";

  public NotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public NotFoundException(final String detail, final Throwable cause) {
    super(detail, cause);
  }

  public NotFoundException(final String detail) {
    super(detail);
  }

  public NotFoundException(final Throwable cause) {
    super(cause);
  }

}
