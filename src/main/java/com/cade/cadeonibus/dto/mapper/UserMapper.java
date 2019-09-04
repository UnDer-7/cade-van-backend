package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
  @Override
  UserDTO toDTO(User entity);

  @Override
  User toEntity(UserDTO dto);
}
