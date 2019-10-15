package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.ItineraryChild;
import com.cade.cadeonibus.dto.ItineraryChildDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ChildMapper.class, ItineraryMapper.class})
public interface ItineraryChildMapper extends EntityMapper<ItineraryChildDTO, ItineraryChild> {

  @Mapping(source = "childId", target = "child")
  @Mapping(source = "itineraryId", target = "itinerary")
  ItineraryChild toEntity(ItineraryChildDTO dto);

  @Mapping(source = "child.id", target = "childId")
  @Mapping(source = "child.name", target = "childName")
  ItineraryChildDTO toDTO(ItineraryChild entity);

  default ItineraryChild fromId(Long id) {
    if (id == null) {
      return null;
    }
    ItineraryChild itineraryChild = new ItineraryChild();
    itineraryChild.setId(id);
    return itineraryChild;
  }
}
