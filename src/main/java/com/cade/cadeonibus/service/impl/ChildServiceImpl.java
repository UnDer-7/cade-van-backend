package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.UserDTO;
import com.cade.cadeonibus.dto.mapper.ChildMapper;
import com.cade.cadeonibus.enums.Perfil;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.ChildService;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.ResponsibleService;
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
  private final ChildMapper childMapper;
  private final UserService userService;
  private final ResponsibleService responsibleService;

  public List<ChildDTO> findAll() {
    log.debug("Request to get all children");

    String login = SecurityUtils.getCurrentUserLogin().get();
    List<Child> childList = null;

    UserDTO user = userService.findByLogin(login);
    if (user.getPerfis().contains(Perfil.DRIVER)) {
      childList = childRepository.findAllByDriverEmail(user.getLogin());
    } else {
      childList = childRepository.findAllByResponsibleEmail(user.getLogin());
    }

    return childMapper.toDTO(childList);
  }

  @Override
  public ChildDTO getOne(Long id) {
    log.debug("Request to get Child -> {}", id);

    Child child = childRepository.getOne(id);
    return childMapper.toDTO(child);
  }

  @Override
  public ChildDTO save(ChildDTO dto) {
    log.debug("Request to save Child -> {}", dto);

    final Driver driver = driverRepository.findByCode(dto.getDriverCode());
    final Child child = childMapper.toEntity(dto);
    child.setDriver(driver);
    final Child saved = childRepository.save(child);
    return childMapper.toDTO(saved);
//    String login = SecurityUtils.getCurrentUserLogin().get();
//    ResponsibleDTO responsibleDTO = responsibleService.findByEmail(login);
//    dto.setResponsibleId(responsibleDTO.getId());

//    Child child = childMapper.toEntity(dto);
//    Child saved = childRepository.save(child);
//    return childMapper.toDTO(saved);
  }
}
