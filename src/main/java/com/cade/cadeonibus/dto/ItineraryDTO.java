package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.ItineraryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ItineraryDTO extends BaseAbstractDTO {
  @NotNull
  private String description;

  @NotNull
  private ItineraryType type;

  @NotNull
  private Long driverId;

  private boolean isAtivo;

  @NotEmpty
  private List<ItineraryChildDTO> itineraryChildren;
}
