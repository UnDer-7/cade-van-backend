package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.*;
import com.cade.cadeonibus.enums.Perfil;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.repository.ResponsibleRepository;
import com.cade.cadeonibus.service.ChatService;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.ResponsibleService;
import com.cade.cadeonibus.service.UserService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
@AllArgsConstructor
public class LocationController {

  private final Logger log = LoggerFactory.getLogger(LocationController.class);
  private final ChatService chatService;
  private final UserService userService;
  private final DriverService driverService;
  private final DriverRepository driverRepository;
  private final ResponsibleService responsibleService;
  private final ResponsibleRepository responsibleRepository;

  @MessageMapping("/location/{driverId}")
  @SendTo("/topic/location/{driverId}")
  public DriverLocationDTO fleetLocation(@DestinationVariable final long driverId,
                                         @Payload final DriverLocationDTO payload) {
    return payload;
  }

  @Transactional
  @MessageMapping("/chat/sendMessage/{chatId}")
  @SendTo("/topic/chat/{chatId}")
  public ChatMessageDTO sendMessage(@DestinationVariable("chatId") Long chatId, @Payload final ChatMessageDTO chatMessage) {
    chatMessage.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    chatService.saveMessage(chatMessage);

    sendNotification(chatMessage);

    return chatMessage;
  }

  private void sendNotification(ChatMessageDTO chatMessage) {
    UserDTO user = userService.findOne(chatMessage.getUserId());
    String token = null;

    if (user.getPerfis().contains(Perfil.RESPONSIBLE)) {
      ResponsibleDTO responsible = responsibleService.findByEmail(user.getLogin());
      ChatDTO chat = chatService.getOneByChatIdAndResponsibleId(chatMessage.getChatId(), responsible.getId());
      Driver driver = driverRepository.getOne(chat.getDriverId());
      token = driver.getUser().getDeviceToken();
    } else {
      DriverDTO driver = driverService.findByEmail(user.getLogin());
      ChatDTO chat = chatService.getOneByChatIAndDriverId(chatMessage.getChatId(), driver.getId());
      Responsible responsible = responsibleRepository.getOne(chat.getResponsibleId());
      token = responsible.getUser().getDeviceToken();
    }

    if (token == null) return;

    try {
      UserResponseDTO completeUser = userService.findByLogin(user.getLogin());
      Notification notification = new Notification(completeUser.getName(), chatMessage.getText());

      Message message = Message.builder()
        .setNotification(notification)
        .setToken(token)
        .build();

      String response = FirebaseMessaging.getInstance().send(message);
      log.info("Message sent {}", response);
    } catch (FirebaseMessagingException e) {
      log.error("Não foi possível enviar a notificação para o usuário, payload: {}", chatMessage);
      e.printStackTrace();
    }
  }
}
