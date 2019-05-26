package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/responsible")
public class ResponsibleResource {
  private final Logger log = LoggerFactory.getLogger(ResponsibleResource.class);

  private final ResponsibleService responsibleService;

  @PostMapping
  public ResponseEntity<Responsible> saveResponsible(@RequestBody Responsible responsible) {
    log.debug("REST request to save Responsible -> {}", responsible);

    Responsible saved = responsibleService.saveResponsible(responsible);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }
}
