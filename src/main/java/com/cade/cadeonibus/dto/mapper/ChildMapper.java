package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.dto.ChildDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ResponsibleMapper.class, DriverMapper.class})
public interface ChildMapper extends EntityMapper<ChildDTO, Child> {

  @Mapping(source = "responsibleId", target = "responsible")
  @Mapping(source = "driverId", target = "driver")
  Child toEntity(ChildDTO dto);

  @Mapping(source = "responsible.id", target = "responsibleId")
  @Mapping(source = "driver.id", target = "driverId")
  ChildDTO toDTO(Child entity);

  default Child fromId(Long id) {
    if (id == null) {
      return null;
    }
    Child child = new Child();
    child.setId(id);
    return child;
  }
}
