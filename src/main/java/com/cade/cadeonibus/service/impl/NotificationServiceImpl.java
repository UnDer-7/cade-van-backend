package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.service.NotificationService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
  private final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

  @Override
  public void sendNotification(final String title, final String body, final String token) {
    final Notification notification = new Notification(title, body);

    final Message message = Message.builder()
      .setNotification(notification)
      .setToken(token)
      .build();

    try {
      final String response = FirebaseMessaging.getInstance().send(message);
      logger.debug("Message sent: {}", response);
    } catch (final FirebaseMessagingException e) {
      logger.error("Não foi possível enviar a notificação para o usuário\tTitle: {} | Body: {}", title, body, e);
    }
  }
}
