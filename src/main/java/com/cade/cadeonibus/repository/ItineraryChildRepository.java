package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Itinerary;
import com.cade.cadeonibus.domain.ItineraryChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryChildRepository extends JpaRepository<ItineraryChild, Long> {
  List<Itinerary> findAllByItinerary(Itinerary itinerary);
}
