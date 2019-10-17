package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.LocationDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LocationController {


  @MessageMapping("/location/{lat}/{lon}/driverId/{driverId}")
  @SendTo("/topic/location/{driverId}")
  public LocationDTO fleetLocation(@DestinationVariable Long driverId, @DestinationVariable double lat, @DestinationVariable double lon) {
    return new LocationDTO(lat, lon, driverId);
  }

}
