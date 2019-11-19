package com.cade.cadeonibus.service;

public interface NotificationService {
  void sendNotification(final String title, final String body, final String token);
}
