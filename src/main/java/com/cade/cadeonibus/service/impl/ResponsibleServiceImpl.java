package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.repository.ResponsibleRepository;
import com.cade.cadeonibus.rest.ResponsibleResource;
import com.cade.cadeonibus.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ResponsibleServiceImpl implements ResponsibleService {
  private final Logger log = LoggerFactory.getLogger(ResponsibleResource.class);
  private final ResponsibleRepository responsibleRepository;

  @Override
  public Responsible saveResponsible(Responsible responsible) {
    log.debug("Request to save Responsible -> {}", responsible);
    return responsibleRepository.save(responsible);
  }
}
