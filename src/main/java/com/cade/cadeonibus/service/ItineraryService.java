package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.ItineraryDTO;

import java.util.List;

public interface ItineraryService {
  void save(ItineraryDTO itinerary) throws Exception;

  List<ItineraryDTO> findAll() throws Exception;
}
