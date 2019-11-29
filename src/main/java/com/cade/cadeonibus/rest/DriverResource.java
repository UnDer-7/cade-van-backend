package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ItineraryDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.ItineraryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/driver")
public class DriverResource {
  private final DriverService driverService;
  private final ItineraryService itineraryService;

  @GetMapping()
  public ResponseEntity<List<DriverDTO>> findAll() {
    List<DriverDTO> drivers = driverService.findAll();
    return ResponseEntity.ok(drivers);
  }

  @GetMapping("/code")
  public ResponseEntity<String> findMyCode() {
    final String code = driverService.findCode();
    return ResponseEntity.ok(code);
  }

  @GetMapping("/my-drivers/{responsibleId}/{driverId}")
  public ResponseEntity<DriverDTO> findResponsibleDriver(@PathVariable long responsibleId, @PathVariable long driverId) {
    final DriverDTO dto = driverService.findResponsibleDriver(responsibleId, driverId);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/my-responsibles/{driverId}")
  public ResponseEntity<Set<ResponsibleDTO>> findResponsibles(@PathVariable Long driverId) {
    final Set<ResponsibleDTO> responsibleList = driverService.findAllResponsibles(driverId);
    return ResponseEntity.ok(responsibleList);
  }


  @GetMapping("/children")
  public ResponseEntity<List<ChildDTO>> findMyChildren() {
    final List<ChildDTO> childDTO = driverService.findMyChildren();
    return ResponseEntity.ok(childDTO);
  }

  @GetMapping("/{itineraryId}")
  public ResponseEntity<ItineraryDTO> findOne(@PathVariable final long itineraryId) {
    final ItineraryDTO dto = itineraryService.findOne(itineraryId);
    return ResponseEntity.ok(dto);
  }
}
