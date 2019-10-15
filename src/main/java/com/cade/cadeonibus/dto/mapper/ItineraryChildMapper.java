package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.ItineraryChild;
import com.cade.cadeonibus.dto.ItineraryChildDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ChildMapper.class, ItineraryMapper.class})
public interface ItineraryChildMapper extends EntityMapper<ItineraryChildDTO, ItineraryChild> {

  ItineraryChild toEntity(ItineraryChildDTO dto);

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
