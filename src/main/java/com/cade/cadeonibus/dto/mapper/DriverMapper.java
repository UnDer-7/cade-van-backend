package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, UserService.class})
public interface DriverMapper extends EntityMapper<DriverDTO, Driver> {
  @Mapping(source = "userId", target = "user")
  Driver toEntity(DriverDTO dto);

  @Mapping(source = "user.id", target = "userId")
  DriverDTO toDTO(Driver entity);
}
