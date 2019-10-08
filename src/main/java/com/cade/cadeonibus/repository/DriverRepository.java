package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
  Driver findByCode(final String code);

  Driver findByEmail(final String email);

  List<Driver> findAllByIdIn(List<Long> id);
}
