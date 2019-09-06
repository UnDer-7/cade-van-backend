package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ResponsibleMapper extends EntityMapper<ResponsibleDTO, Responsible> {

  @Mapping(source = "userId", target = "user")
  Responsible toEntity(ResponsibleDTO dto);

  @Mapping(source = "user.id", target = "userId")
  ResponsibleDTO toDTO(Responsible entity);

  default Responsible fromId(Long id) {
    if (id == null) {
      return null;
    }
    Responsible responsible = new Responsible();
    responsible.setId(id);
    return responsible;
  }
}
