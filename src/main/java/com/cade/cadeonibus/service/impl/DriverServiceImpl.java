package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.dao.DriverDAO;
import com.cade.cadeonibus.dto.mapper.ChildMapper;
import com.cade.cadeonibus.dto.mapper.DriverMapper;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
  private final Logger log = LoggerFactory.getLogger(DriverServiceImpl.class);

  private final DriverRepository driverRepository;
  private final ChildRepository childRepository;

  private final ResponsibleService responsibleService;

  private final DriverMapper driverMapper;
  private final ChildMapper childMapper;

  @Override
  public DriverDTO save(DriverDTO driverDTO) {
    Driver driver = driverMapper.toEntity(driverDTO);
    driver = driverRepository.save(driver);
    return driverMapper.toDTO(driver);
  }

  @Override
  public List<DriverDTO> findAll() {
    String login = SecurityUtils.getCurrentUserLogin().get();
    ResponsibleDTO responsible = responsibleService.findByEmail(login);
    List<Long> driverIds = childRepository.findAllByResponsibleId(responsible.getId())
      .stream().map(child -> child.getDriver().getId()).collect(Collectors.toList());
    List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);
    return driverMapper.toDTO(drivers);
  }

  @Override
  public DriverDTO findByEmail(final String email) {
    final Driver driver = driverRepository.findByEmail(email);
    return driverMapper.toDTO(driver);
  }

  @Override
  public DriverDTO findResponsibleDriver(final long responsibleId, final long driverId) {
    final DriverDAO driver = driverRepository.findResponsibleDrivers(responsibleId, driverId);
    return new DriverDTO(driver);
  }

  @Override
  public List<ChildDTO> findMyChildren() {
    final String login = SecurityUtils.getCurrentUserLogin()
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não está logado"));

    final Driver driver = driverRepository.findByEmail(login);
    final List<Child> child = childRepository.findAllByDriverId(driver.getId());
    return childMapper.toDTO(child);
  }
}
