package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ChatMessageDTO;
import com.cade.cadeonibus.dto.DriverLocationDTO;
import com.cade.cadeonibus.service.ChatService;
import com.cade.cadeonibus.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
@AllArgsConstructor
public class LocationController {

  private final Logger log = LoggerFactory.getLogger(LocationController.class);
  private final ChatService chatService;
  private final UserService userService;

  @MessageMapping("/location/{driverId}")
  @SendTo("/topic/location/{driverId}")
  public DriverLocationDTO fleetLocation(@DestinationVariable final long driverId,
                                         @Payload final DriverLocationDTO payload) {
    return payload;
  }

  @MessageMapping("/chat/sendMessage/{chatId}")
  @SendTo("/topic/chat/{chatId}")
  public ChatMessageDTO sendMessage(@DestinationVariable("chatId") Long chatId, @Payload final ChatMessageDTO chatMessage) {
    chatMessage.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    chatService.saveMessage(chatMessage);
    return chatMessage;
  }
}
