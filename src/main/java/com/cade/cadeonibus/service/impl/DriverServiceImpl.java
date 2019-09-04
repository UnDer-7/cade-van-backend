package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.mapper.DriverMapper;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
  private final DriverMapper driverMapper;
  private final DriverRepository driverRepository;

  @Override
  public DriverDTO save(DriverDTO driverDTO) {
      Driver driver = driverMapper.toEntity(driverDTO);
      driver = driverRepository.save(driver);
      return driverMapper.toDTO(driver);
  }
}
