package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.mapper.ResponsibleMapper;
import com.cade.cadeonibus.repository.ResponsibleRepository;
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
  private final Logger log = LoggerFactory.getLogger(ResponsibleServiceImpl.class);

  private final ResponsibleRepository responsibleRepository;

  private final ResponsibleMapper responsibleMapper;

  @Override
  public ResponsibleDTO getOne(Long id) {
    log.debug("Request to get Responsible -> ID: {}", id);

    Responsible responsible = responsibleRepository.getOne(id);
    return responsibleMapper.toDTO(responsible);
  }

  @Override
  public ResponsibleDTO save(ResponsibleDTO dto) {
    log.debug("Request to save Responsible -> {}", dto);

    Responsible responsible = responsibleMapper.toEntity(dto);
    Responsible saved = responsibleRepository.save(responsible);
    return responsibleMapper.toDTO(saved);
  }

  @Override
  public ResponsibleDTO findByEmail(String email) {
    Responsible responsible = responsibleRepository.findByEmail(email);
    return responsibleMapper.toDTO(responsible);
  }
}
