package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.ResponsibleDTO;

public interface ResponsibleService {
  ResponsibleDTO getOne(final Long id);
  ResponsibleDTO getOne(final String email);
  ResponsibleDTO save(ResponsibleDTO responsible);

  ResponsibleDTO findByEmail(String email);
}
