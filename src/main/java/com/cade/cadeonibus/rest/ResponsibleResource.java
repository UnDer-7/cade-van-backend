package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/responsible")
public class ResponsibleResource {
  private static final String DEFAULT_LOG_MESSAGE = "REST request to";
  private final Logger log = LoggerFactory.getLogger(ResponsibleResource.class);
  private final ResponsibleService responsibleService;

  private final UtilResponses<ResponsibleDTO> utilResponses;

  @GetMapping("/{id}")
  public ResponseEntity<ResponsibleDTO> getResponsible(@PathVariable Long id) {
    log.info("{} get Responsible -> {}", DEFAULT_LOG_MESSAGE, id);
    ResponsibleDTO dto = responsibleService.getOne(id);
    return utilResponses.successResponse(dto);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<ResponsibleDTO> getResponsible(@PathVariable final String email) {
    log.info("{} get Responsible -> {}", DEFAULT_LOG_MESSAGE, email);
    ResponsibleDTO dto = responsibleService.getOne(email);
    return utilResponses.successResponse(dto);
  }

  @PostMapping
  public ResponseEntity<ResponsibleDTO> saveResponsible(@RequestBody ResponsibleDTO dto) {
    log.info("{} save Responsible -> {}", DEFAULT_LOG_MESSAGE, dto);

    ResponsibleDTO saved = responsibleService.save(dto);
    return utilResponses.successResponse(HttpStatus.CREATED, saved);
  }

  @GetMapping("/my-drivers")
  public ResponseEntity<List<DriverDTO>> getMyDrivers() {
    List<DriverDTO> dto = responsibleService.findMyDrivers();
    return ResponseEntity.ok(dto);
  }
}
