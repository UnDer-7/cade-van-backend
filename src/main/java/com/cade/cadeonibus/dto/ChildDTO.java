package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.ChildStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ChildDTO extends BaseAbstractDTO {

  private Long id;

  @NotEmpty
  private String name;

  @NotEmpty
  private String school;

  @NotEmpty
  private LocalDate birthDate;

  @NotEmpty
  private String period;

  private ChildStatus status;

  @JsonIgnore
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private ResponsibleDTO responsible;

  @JsonIgnore
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private UserResponseDTO userResponse;

  private String driverCode;

  private Long driverId;

  @JsonProperty("responsible")
  public ResponsibleDTO getResponsible() {
//    if (responsible == null) {
//      responsible = Objects.requireNonNullElseGet(null, () -> new ResponsibleDTO(userResponse));
//    }
    return responsible;
  }

  @JsonProperty("responsible")
  public void setResponsible(ResponsibleDTO responsible) {
    this.responsible = Objects.requireNonNullElseGet(responsible, () -> new ResponsibleDTO(userResponse));
  }

  @JsonProperty("userResponse")
  public UserResponseDTO getUserResponse() {
    return new UserResponseDTO(responsible);
  }

  @JsonProperty("userResponse")
  public void setUserResponse(final ResponsibleDTO responsible) {
    this.userResponse = new UserResponseDTO(responsible);
  }
}
