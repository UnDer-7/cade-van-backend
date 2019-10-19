package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.ChildStatusDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import com.cade.cadeonibus.dto.mapper.ChildMapper;
import com.cade.cadeonibus.enums.ChildStatus;
import com.cade.cadeonibus.enums.Perfil;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.repository.ResponsibleRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.ChildService;
import com.cade.cadeonibus.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ChildServiceImpl implements ChildService {
  private final Logger log = LoggerFactory.getLogger(ChildServiceImpl.class);

  private final ChildRepository childRepository;
  private final DriverRepository driverRepository;
  private final ResponsibleRepository responsibleRepository;
  private final ChildMapper childMapper;
  private final UserService userService;

  @Override
  public List<ChildDTO> findAll() {
    log.debug("Request to get all children");

    final String login = SecurityUtils.getCurrentUserLogin().orElse(null);

    final UserResponseDTO user = userService.findByLogin(login);

    if (user.getPerfil() == Perfil.DRIVER) {
      final List<Child> children = childRepository.findAllByDriverEmail(user.getEmail());
      return childMapper.toDTO(children);
    }
    final List<Child> children = childRepository.findAllByResponsibleEmail(user.getEmail());
    return childMapper.toDTO(children);
  }

  @Override
  public ChildDTO getOne(Long id) {
    log.debug("Request to get Child -> {}", id);

    Child child = childRepository.getOne(id);
    return childMapper.toDTO(child);
  }

  @Override
  public void save(ChildDTO dto) {
    log.debug("Request to save Child -> {}", dto);

    final Driver driver = driverRepository.findByCode(dto.getDriverCode());
    final String email = SecurityUtils.getCurrentUserLogin().orElse(null);
    final Responsible responsible = responsibleRepository.findByEmail(email);
    final Child child = childMapper.toEntity(dto);

    child.setResponsible(responsible);
    child.setDriver(driver);
    child.setStatus(ChildStatus.LEFT_HOME);
    childRepository.save(child);
  }

  @Override
  public void updateStatus(ChildStatusDTO childStatus) {
    Child child = childRepository.getOne(childStatus.getChildId());
    child.setStatus(childStatus.getStatus());
  }
}
