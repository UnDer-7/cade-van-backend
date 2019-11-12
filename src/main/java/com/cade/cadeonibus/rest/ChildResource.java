package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.ChildStatusDTO;
import com.cade.cadeonibus.service.ChildService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    ChildDTO dto = childService.getOne(id);
    return utilResponses.successResponse(dto);
  }

  @GetMapping
  public ResponseEntity<List<ChildDTO>> findAll() {
    log.debug("REST request to get all children");

    List<ChildDTO> childList = childService.findAll();
    return new ResponseEntity<>(childList, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody ChildDTO child) {
    log.debug("REST request to save Child -> {}", child);

    childService.save(child);
    return utilResponses.successResponse(HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<ChildDTO> updateChild(@RequestBody final ChildDTO childDTO) {
    log.debug("REST request to update child Status -> {}", childDTO);

    final ChildDTO dto = childService.update(childDTO);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/itinerary/{itineraryId}")
  public ResponseEntity<Void> updateStatusWaiting(@PathVariable final long itineraryId) {
    childService.updateStatusToWaiting(itineraryId);
    return ResponseEntity.ok().build();
  }
}
