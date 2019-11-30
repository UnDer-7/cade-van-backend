package com.cade.cadeonibus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChatDTO extends BaseAbstractDTO {
  private Long driverId;
  private Long responsibleId;
  private List<ChatMessageDTO> messages;
}
