package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Itinerary;
import com.cade.cadeonibus.dto.ItineraryDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import com.cade.cadeonibus.dto.mapper.ItineraryMapper;
import com.cade.cadeonibus.repository.ItineraryChildRepository;
import com.cade.cadeonibus.repository.ItineraryRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.ItineraryService;
import com.cade.cadeonibus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

  private final ItineraryRepository itineraryRepository;
  private final ItineraryChildRepository itineraryChildRepository;
  private final ItineraryMapper itineraryMapper;
  private final UserService userService;

  @Override
  public void save(ItineraryDTO itineraryDTO) {
    Itinerary itinerary = itineraryMapper.toEntity(itineraryDTO);
    itinerary = itineraryRepository.save(itinerary);
    itinerary.getItineraryChildList().forEach(itineraryChildRepository::save);
  }

  @Override
  public List<ItineraryDTO> findAll() {
    final String login = SecurityUtils.getCurrentUserLogin().orElse(null);
    final UserResponseDTO user = userService.findByLogin(login);
    List<Itinerary> itineraryList = itineraryRepository.findAllByDriverEmail(user.getEmail());
    return itineraryMapper.toDTO(itineraryList);
  }
}
