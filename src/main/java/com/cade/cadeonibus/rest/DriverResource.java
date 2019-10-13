package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.service.DriverService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/driver")
public class DriverResource {

  private final Logger log = LoggerFactory.getLogger(DriverResource.class);

  private final DriverService driverService;

  @GetMapping()
  public ResponseEntity<List<DriverDTO>> findAll() {
    List<DriverDTO> drivers = driverService.findAll();
    return ResponseEntity.ok(drivers);
  }

  @GetMapping("/my-drivers/{responsibleId}/{driverId}")
  public ResponseEntity<DriverDTO> findResponsibleDriver(@PathVariable long responsibleId, @PathVariable long driverId) {
    final DriverDTO dto = driverService.findResponsibleDriver(responsibleId, driverId);
    return ResponseEntity.ok(dto);
  }
}
