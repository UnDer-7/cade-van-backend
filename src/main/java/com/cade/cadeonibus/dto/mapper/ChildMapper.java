package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {ResponsibleMapper.class, DriverMapper.class})
public interface ChildMapper extends EntityMapper<ChildDTO, Child> {

  default Child toEntity(final ChildDTO dto) {
    final Child child = new Child();
    final Responsible responsible = new Responsible();
    final User user = new User();
    final Driver driver = new Driver();

    child.setId(dto.getId());
    child.setName(dto.getName());
    child.setSchool(dto.getSchool());
    child.setBirthDate(dto.getBirthDate());
    child.setPeriod(dto.getPeriod());
    child.setStatus(dto.getStatus());

    responsible
      .setId(Objects.requireNonNullElseGet(
        dto.getUserResponse().getResponsibleId(), () -> dto.getResponsible().getId())
      );
    responsible.setName(Objects.nonNull(dto.getResponsible()) ? dto.getResponsible().getName() : null);
    responsible.setEmail(Objects.nonNull(dto.getResponsible()) ? dto.getResponsible().getEmail() : null);
    responsible.setPhone(Objects.nonNull(dto.getResponsible()) ? dto.getResponsible().getPhone(): null);

    user.setId(Objects.requireNonNullElseGet(
      dto.getUserResponse().getUserId(), () -> dto.getResponsible().getUserId()
    ));
    responsible.setUser(user);
    child.setResponsible(responsible);

    driver.setId(dto.getDriverId());
    child.setDriver(driver);
    return child;
  }

  default ChildDTO toDTO(final Child entity) {
    final ChildDTO dto = new ChildDTO();
    final ResponsibleDTO responsibleDTO = new ResponsibleDTO();

    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setSchool(entity.getSchool());
    dto.setBirthDate(entity.getBirthDate());
    dto.setPeriod(entity.getPeriod());
    dto.setStatus(entity.getStatus());

    responsibleDTO.setId(entity.getResponsible().getId());
    responsibleDTO.setName(entity.getResponsible().getName());
    responsibleDTO.setEmail(entity.getResponsible().getEmail());
    responsibleDTO.setPhone(entity.getResponsible().getPhone());
    responsibleDTO.setUserId(entity.getResponsible().getUser().getId());
    dto.setResponsible(responsibleDTO);

    dto.setUserResponse(new UserResponseDTO(responsibleDTO));
    dto.setDriverCode(entity.getDriver().getCode());
    dto.setDriverId(entity.getDriver().getId());
    return dto;
  }

//  @Mapping(source = "driverId", target = "driver")
//  Child toEntity(ChildDTO dto);

//  @Mapping(source = "driver.id", target = "driverId")
//  ChildDTO toDTO(Child entity);
}
