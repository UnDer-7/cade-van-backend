package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.ResponsibleDTO;

public interface ResponsibleService {
  ResponsibleDTO getOne(Long id);
  ResponsibleDTO save(ResponsibleDTO responsible);

  ResponsibleDTO findByEmail(String email);
}
