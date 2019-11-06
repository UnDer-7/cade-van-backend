package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.mapper.DriverMapper;
import com.cade.cadeonibus.dto.mapper.ResponsibleMapper;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.repository.ResponsibleRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ResponsibleServiceImpl implements ResponsibleService {
  private final Logger log = LoggerFactory.getLogger(ResponsibleServiceImpl.class);

  private final ResponsibleRepository responsibleRepository;
  private final DriverRepository driverRepository;
  private final ChildRepository childRepository;

  private final ResponsibleMapper responsibleMapper;
  private final DriverMapper driverMapper;

  @Override
  public ResponsibleDTO getOne(final Long id) {
    log.debug("Request to get Responsible -> ID: {}", id);

    Responsible responsible = responsibleRepository.getOne(id);
    return responsibleMapper.toDTO(responsible);
  }

  @Override
  public ResponsibleDTO getOne(final String email) {
    log.debug("Request to get Responsible -> Email: {}", email);

    Responsible responsible = responsibleRepository.findByEmail(email);
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

  @Override
  public List<DriverDTO> findMyDrivers() {
    final String login = SecurityUtils.getCurrentUserLogin()
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não está logado"));

    final List<Child> children = childRepository.findAllByResponsibleEmail(login);
    if (children == null || children.isEmpty()) {
      return new ArrayList<>();
    }

    return children
      .stream()
      .map(Child::getDriver)
      .collect(Collectors.toUnmodifiableList())
      .stream()
      .map(Driver::getId)
      .map(driverRepository::findById)
      .map(Optional::orElseThrow)
      .map(driverMapper::toDTO)
      .collect(Collectors.toUnmodifiableList());
  }
}
