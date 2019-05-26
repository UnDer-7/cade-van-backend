package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.service.ChildService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/child")
public class ChildResource {
  private final Logger log = LoggerFactory.getLogger(ChildResource.class);

  private final ChildService childService;

  private final UtilResponses<ChildDTO> utilResponses;

  @GetMapping("/{id}")
  public ResponseEntity<ChildDTO> getChild(@PathVariable Long id) {
    log.debug("REST request to get child -> ID: {}", id);

    ChildDTO dto = childService.getChild(id);
    return utilResponses.successResponse(dto);
  }

  @PostMapping
  public ResponseEntity<ChildDTO> saveResponsible(@RequestBody ChildDTO child) {
    log.debug("REST request to save Responsible -> {}", child);

    ChildDTO dto = childService.saveChild(child);
    return utilResponses.successResponse(HttpStatus.CREATED, dto);
  }
}
