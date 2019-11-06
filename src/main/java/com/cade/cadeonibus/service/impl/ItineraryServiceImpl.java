package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Itinerary;
import com.cade.cadeonibus.domain.ItineraryChild;
import com.cade.cadeonibus.dto.ItineraryChildDTO;
import com.cade.cadeonibus.dto.ItineraryDTO;
import com.cade.cadeonibus.dto.mapper.ItineraryChildMapper;
import com.cade.cadeonibus.dto.mapper.ItineraryMapper;
import com.cade.cadeonibus.enums.ChildStatus;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.repository.ItineraryChildRepository;
import com.cade.cadeonibus.repository.ItineraryRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.ItineraryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
  private final ChildRepository childRepository;

  private final ItineraryMapper itineraryMapper;
  private final ItineraryChildMapper itineraryChildMapper;

  @Override
  public void save(ItineraryDTO itineraryDTO) {
    Itinerary itinerary = itineraryMapper.toEntity(itineraryDTO);

    final String login = SecurityUtils.getCurrentUserLogin()
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não está logado"));

    final Driver driver = driverRepository.findByEmail(login);
    itinerary.setDriver(driver);

    final Itinerary itinerarySaved = itineraryRepository.save(itinerary);
    itineraryDTO.getItineraryChildren().forEach(item -> item.setItinerary(itineraryMapper.toDTO(itinerarySaved)));
    itineraryChildRepository.saveAll(itineraryChildMapper.toEntity(itineraryDTO.getItineraryChildren()));

    LOGGER.info("Itinerary saved");
  }

  @Override
  public void updateAllChildrenToWaiting(final long itineraryId) {
    final boolean existeItineraryActivated = itineraryRepository.existsByIsAtivoTrue();
    if (existeItineraryActivated) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um itinerário em andamento");
    }

    itineraryRepository
      .findById(itineraryId)
      .stream()
      .peek(item -> item.setAtivo(true))
      .forEach(itineraryRepository::save);

    itineraryChildRepository.findAllByItineraryId(itineraryId)
      .stream()
      .map(ItineraryChild::getChild)
      .peek(item -> item.setStatus(ChildStatus.WAITING))
      .forEach(childRepository::save);
  }

  @Override
  public void finishItinerary(final long itineraryId) {
    itineraryRepository.findById(itineraryId)
      .stream()
      .peek(item -> item.setAtivo(false))
      .forEach(itineraryRepository::save);
  }

  @Override
  public List<ItineraryDTO> findAll() {
    final String login = SecurityUtils.getCurrentUserLogin()
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não está logado"));

    LOGGER.info("Requesto to get All Itinerary from user: {}", login);
    final List<ItineraryDTO> itineraryDTO =
      new ArrayList<>(itineraryMapper.toDTO(itineraryRepository.findAllByDriverEmail(login)));
    itineraryDTO.forEach(item -> item.setItineraryChildren(itineraryChildMapper.toDTO(itineraryChildRepository.findAllByItineraryId(item.getId()))));
    return itineraryDTO;
  }

  @Override
  public ItineraryDTO findOne(final long itineraryId) {
    final Itinerary itinerary = itineraryRepository.findById(itineraryId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Itinerário não encontrado"));
    final List<ItineraryChild> itineraryChildren = itineraryChildRepository.findAllByItineraryId(itinerary.getId());

    final ItineraryDTO itineraryDTO = itineraryMapper.toDTO(itinerary);
    final List<ItineraryChildDTO> itineraryChildDTOS = itineraryChildMapper.toDTO(itineraryChildren);

    itineraryDTO.setItineraryChildren(itineraryChildDTOS);
    return itineraryDTO;
  }
}
