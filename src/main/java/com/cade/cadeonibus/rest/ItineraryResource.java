package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ItineraryDTO;
import com.cade.cadeonibus.service.ItineraryService;
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
@RequestMapping("/api/itinerary")
public class ItineraryResource {

  private static final String DEFAULT_LOG_MESSAGE = "REST request to";
  private final Logger log = LoggerFactory.getLogger(ItineraryResource.class);
  private final ItineraryService itineraryService;
  private final UtilResponses<ItineraryDTO> utilResponses;

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody ItineraryDTO itinerary) {
    log.debug("REST request to save Itinerary -> {}", itinerary);

    itineraryService.save(itinerary);
    return utilResponses.successResponse(HttpStatus.CREATED);
  }
}
