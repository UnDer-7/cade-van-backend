package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;

import java.util.List;

public interface ResponsibleService {
  ResponsibleDTO getOne(final Long id);

  ResponsibleDTO getOne(final String email);

  ResponsibleDTO save(ResponsibleDTO responsible);

  ResponsibleDTO findByEmail(String email);

  List<DriverDTO> findMyDrivers() throws Exception;
}
