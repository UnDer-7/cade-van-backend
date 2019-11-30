package com.cade.cadeonibus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChatMessageDTO extends BaseAbstractDTO {
  private Long userId;
  private String userName;
  private Long chatId;
  private String text;
  private LocalDateTime createdAt;
}
