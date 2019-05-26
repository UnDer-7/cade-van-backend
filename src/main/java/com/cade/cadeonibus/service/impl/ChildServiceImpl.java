package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.mapper.ChildMapper;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.service.ChildService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ChildServiceImpl implements ChildService {
  private final Logger log = LoggerFactory.getLogger(ChildServiceImpl.class);

  private final ChildRepository childRepository;

  private final ChildMapper childMapper;

  @Override
  public ChildDTO getChild(Long id) {
    log.debug("Request to get Child -> {}", id);

    Child child = childRepository.getOne(id);
    return childMapper.toDTO(child);
  }

  @Override
  public ChildDTO saveChild(ChildDTO dto) {
    log.debug("Request to save Child -> {}", dto);

    Child child = childMapper.toEntity(dto);
    Child saved = childRepository.save(child);
    return childMapper.toDTO(saved);
  }
}
