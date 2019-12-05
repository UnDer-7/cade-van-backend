package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ChatMessageDTO;
import com.cade.cadeonibus.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
@AllArgsConstructor
public class ChatController {
  private final ChatService chatService;

  @MessageMapping("/chat/sendMessage/{chatId}")
  @SendTo("/topic/chat/{chatId}")
  public ChatMessageDTO sendMessage(@DestinationVariable("chatId") Long chatId, @Payload final ChatMessageDTO chatMessage) {
    chatMessage.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

    final ChatMessageDTO message = chatService.saveMessage(chatMessage);
    chatService.sendNotification(chatMessage);

    return message;
  }
}
