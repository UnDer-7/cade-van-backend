package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ItineraryDTO;
import com.cade.cadeonibus.service.ItineraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/itinerary")
public class ItineraryResource {
  private final ItineraryService itineraryService;
  private final UtilResponses<ItineraryDTO> utilResponses;

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody ItineraryDTO itinerary) {
    itineraryService.save(itinerary);
    return utilResponses.successResponse(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ItineraryDTO>> getAll() {
    final List<ItineraryDTO> dtos = itineraryService.findAll();
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/init/{itineraryId}")
  public ResponseEntity<Void> initItinerary(@PathVariable final long itineraryId) {
    itineraryService.updateAllChildrenToWaiting(itineraryId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/finish/{itineraryId}")
  public ResponseEntity<Void> finishItinerary(@PathVariable final long itineraryId) {
    itineraryService.finishItinerary(itineraryId);
    return ResponseEntity.ok().build();
  }
}
