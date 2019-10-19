package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.ChildStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChildStatusDTO {

  private Long childId;
  private ChildStatus status;
}
