package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.DriverDTO;

import java.util.List;

public interface DriverService {
  DriverDTO save(DriverDTO driverDTO);

  List<DriverDTO> findAll();
}
