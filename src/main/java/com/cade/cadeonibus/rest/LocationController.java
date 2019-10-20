package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.DriverLocationDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LocationController {
  @MessageMapping("/location/{driverId}")
  @SendTo("/topic/location/{driverId}")
  public DriverLocationDTO fleetLocation(@DestinationVariable final long driverId, @Payload final DriverLocationDTO payload) {
    return payload;
  }
}
