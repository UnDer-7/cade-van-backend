package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.mapper.DriverMapper;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.ResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
  private final DriverMapper driverMapper;
  private final DriverRepository driverRepository;
  private final ChildRepository childRepository;
  private final ResponsibleService responsibleService;

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
}
