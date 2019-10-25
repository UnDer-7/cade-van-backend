package com.cade.cadeonibus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO extends BaseAbstractDTO {

  private Long userId;
  private Long chatId;
  private String text;
  private LocalDateTime createdAt;
}
