package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.DriverLocationDTO;

public interface LocationService {
  void sendNotification(final DriverLocationDTO payload);
}
