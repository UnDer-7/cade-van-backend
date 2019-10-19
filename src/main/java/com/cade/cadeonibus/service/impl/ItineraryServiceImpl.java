package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Itinerary;
import com.cade.cadeonibus.dto.ItineraryDTO;
import com.cade.cadeonibus.dto.mapper.ItineraryChildMapper;
import com.cade.cadeonibus.dto.mapper.ItineraryMapper;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.repository.ItineraryChildRepository;
import com.cade.cadeonibus.repository.ItineraryRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.ItineraryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryServiceImpl.class);

  private final ItineraryRepository itineraryRepository;
  private final ItineraryChildRepository itineraryChildRepository;
  private final DriverRepository driverRepository;

  private final ItineraryMapper itineraryMapper;
  private final ItineraryChildMapper itineraryChildMapper;

  @Override
  public void save(ItineraryDTO itineraryDTO) throws Exception {
    Itinerary itinerary = itineraryMapper.toEntity(itineraryDTO);

    final String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new Exception("Usuario nao esta logado"));
    final Driver driver = driverRepository.findByEmail(login);
    itinerary.setDriver(driver);

    final Itinerary itinerarySaved = itineraryRepository.save(itinerary);
    itineraryDTO.getItineraryChildren().forEach(item -> item.setItinerary(itineraryMapper.toDTO(itinerarySaved)));
    itineraryChildRepository.saveAll(itineraryChildMapper.toEntity(itineraryDTO.getItineraryChildren()));

    LOGGER.info("Itinerary saved");
  }

  @Override
  public List<ItineraryDTO> findAll() throws Exception {
    final String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new Exception("Usuario nao esta logado"));
    LOGGER.info("Requesto to get All Itinerary from user: {}", login);
    final List<ItineraryDTO> itineraryDTO = new ArrayList<>(itineraryMapper.toDTO(itineraryRepository.findAllByDriverEmail(login)));
    itineraryDTO.forEach(item -> item.setItineraryChildren(itineraryChildMapper.toDTO(itineraryChildRepository.findAllByItineraryId(item.getId()))));
    return itineraryDTO;
  }
}
