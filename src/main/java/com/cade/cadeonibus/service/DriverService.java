package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.DriverDTO;

import java.util.List;

public interface DriverService {
  DriverDTO save(DriverDTO driverDTO);

  List<DriverDTO> findAll();

  DriverDTO findByEmail(final String email);

  String findCode();

  DriverDTO findResponsibleDriver(final long responsibleId, final long driverId);

  DriverDTO findOne(final long id);

  List<ChildDTO> findMyChildren();
}
