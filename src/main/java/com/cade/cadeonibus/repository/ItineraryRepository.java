package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
  List<Itinerary> findAllByDriver(Driver driver);

  List<Itinerary> findAllByDriverEmail(String email);

  boolean existsByIsAtivoTrue();
}
