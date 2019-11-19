package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.dto.DriverLocationDTO;
import com.cade.cadeonibus.service.LocationService;
import com.cade.cadeonibus.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
  private final NotificationService notificationService;

  @Async
  @Override
  public void sendNotification(final DriverLocationDTO payload) {
    notificationService.sendNotification("TITLE", "BODY FILHO DA PUTA", "fAagqZYbNPk:APA91bFDO7CNttyHMWrNDnUUVSorrVpgZVcxA6-X0CwasB8aPN6Y_eO_kVncPajotePGnX-OgS78bROvBiF_XBmxFC6DYDKl6ZRGfNgBDGZ4PiH7R-5JGUYPf5M-XT6B9AiPGybZVG4i");
  }
}
