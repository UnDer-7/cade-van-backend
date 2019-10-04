package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.service.DriverService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<?> findAll() {
    List<DriverDTO> drivers = driverService.findAll();
    return new ResponseEntity<>(drivers, HttpStatus.OK);
  }
}
