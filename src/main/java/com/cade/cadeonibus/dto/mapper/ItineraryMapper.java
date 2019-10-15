package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Itinerary;
import com.cade.cadeonibus.dto.ItineraryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DriverMapper.class, ChildMapper.class})
public interface ItineraryMapper extends EntityMapper<ItineraryDTO, Itinerary> {

  @Mapping(source = "driverId", target = "driver")
  Itinerary toEntity(ItineraryDTO dto);

  @Mapping(source = "driver.id", target = "driverId")
  ItineraryDTO toDTO(Itinerary entity);

  default Itinerary fromId(Long id) {
    if (id == null) {
      return null;
    }
    Itinerary itinerary = new Itinerary();
    itinerary.setId(id);
    return itinerary;
  }
}
