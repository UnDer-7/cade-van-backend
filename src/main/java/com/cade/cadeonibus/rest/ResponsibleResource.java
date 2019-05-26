package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.service.ResponsibleService;
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
@RequestMapping("/api/responsible")
public class ResponsibleResource {
  private final Logger log = LoggerFactory.getLogger(ResponsibleResource.class);
  private static final String DEFAULT_LOG_MESSAGE = "REST request to";

  private final ResponsibleService responsibleService;

  private final UtilResponses<ResponsibleDTO> utilResponses;

  @GetMapping("/{id}")
  public ResponseEntity<ResponsibleDTO> getResponsible(@PathVariable Long id) {
    log.debug("{} get Responsible -> {}", DEFAULT_LOG_MESSAGE, id);
    ResponsibleDTO dto = responsibleService.getResponsible(id);
    return utilResponses.successResponse(dto);
  }

  @PostMapping
  public ResponseEntity<ResponsibleDTO> saveResponsible(@RequestBody ResponsibleDTO dto) {
    log.debug("{} save Responsible -> {}", DEFAULT_LOG_MESSAGE, dto);

    ResponsibleDTO saved = responsibleService.saveResponsible(dto);
    return utilResponses.successResponse(HttpStatus.CREATED, saved);
  }
}
