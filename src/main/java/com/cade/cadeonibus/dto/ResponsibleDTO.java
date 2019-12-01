package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.Perfil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ResponsibleDTO extends BaseAbstractDTO {
  private Long id;
  private String name;
  private String email;
  private String phone;
  private Long userId;
  private Perfil perfil = Perfil.RESPONSIBLE;

  public ResponsibleDTO(UserRegisterDTO userRegisterDTO, Long userId) {
    this.name = userRegisterDTO.getName();
    this.email = userRegisterDTO.getEmail();
    this.phone = userRegisterDTO.getPhone();
    this.userId = userId;
  }

  public ResponsibleDTO(final UserResponseDTO dto) {
    this.id = dto.getResponsibleId();
    this.name = dto.getName();
    this.email = dto.getEmail();
    this.phone = dto.getPhone();
    this.userId = dto.getUserId();
  }
}
