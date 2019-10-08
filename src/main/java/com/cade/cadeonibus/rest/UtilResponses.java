package com.cade.cadeonibus.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UtilResponses<E> {
  ResponseEntity<E> successResponse(HttpStatus status, E body) {
    return ResponseEntity.status(status).body(body);
  }

  ResponseEntity<Void> successResponse(HttpStatus status) {
    return ResponseEntity.status(status).build();
  }

  ResponseEntity<E> successResponse(E body) {
    return ResponseEntity.status(HttpStatus.OK).body(body);
  }
}
