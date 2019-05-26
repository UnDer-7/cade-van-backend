package com.cade.cadeonibus.service;

import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ResponsibleDTO;

public interface ResponsibleService {
  ResponsibleDTO getResponsible(Long id);
  ResponsibleDTO saveResponsible(ResponsibleDTO responsible);
}
