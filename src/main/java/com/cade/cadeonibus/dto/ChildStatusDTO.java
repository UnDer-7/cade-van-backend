package com.cade.cadeonibus.dto;

import com.cade.cadeonibus.enums.ChildStatus;
import lombok.Data;

@Data
public class ChildStatusDTO {

  private Long childId;
  private ChildStatus status;
}
