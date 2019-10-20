package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ItineraryDTO;
import com.cade.cadeonibus.service.ItineraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/itinerary")
public class ItineraryResource {
  private final ItineraryService itineraryService;
  private final UtilResponses<ItineraryDTO> utilResponses;

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody ItineraryDTO itinerary) throws Exception {
    itineraryService.save(itinerary);
    return utilResponses.successResponse(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ItineraryDTO>> getAll() throws Exception {
    final List<ItineraryDTO> dtos = itineraryService.findAll();
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/init/{itineraryId}")
  public ResponseEntity<Void> initItinerary(@PathVariable final long itineraryId) throws Exception {
    itineraryService.updateAllChildrenToWaiting(itineraryId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/finish/{itineraryId}")
  public ResponseEntity<Void> finishItinerary(@PathVariable final long itineraryId) {
    itineraryService.finishItinerary(itineraryId);
    return ResponseEntity.ok().build();
  }
}
