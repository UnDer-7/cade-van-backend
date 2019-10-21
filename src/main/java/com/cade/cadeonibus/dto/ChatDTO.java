package com.cade.cadeonibus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO extends BaseAbstractDTO {
  private Long driverId;
  private Long responsibleId;
  private List<ChatMessageDTO> messages;
}
