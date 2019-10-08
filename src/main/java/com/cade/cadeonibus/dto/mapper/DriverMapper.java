package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.dto.DriverDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DriverMapper extends EntityMapper<DriverDTO, Driver> {
  @Mapping(source = "userId", target = "user")
  Driver toEntity(DriverDTO dto);

  @Mapping(source = "user.id", target = "userId")
  DriverDTO toDTO(Driver entity);

  default Driver fromId(Long id) {
    if (id == null) {
      return null;
    }
    Driver driver = new Driver();
    driver.setId(id);
    return driver;
  }
}
