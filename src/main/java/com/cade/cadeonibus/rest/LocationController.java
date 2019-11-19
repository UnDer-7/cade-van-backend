package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.*;
import com.cade.cadeonibus.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class LocationController {
  private final LocationService locationService;

  @MessageMapping("/location/{driverId}")
  @SendTo("/topic/location/{driverId}")
  public DriverLocationDTO fleetLocation(@DestinationVariable final long driverId,
                                         @Payload final DriverLocationDTO payload) {
    locationService.sendNotification(payload);
    return payload;
  }
}
