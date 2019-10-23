package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.dto.dao.DriverDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
  Driver findByCode(final String code);
  Driver findByEmail(final String email);
  List<Driver> findAllByIdIn(List<Long> id);

  @Query(
    nativeQuery = true,
    value = "SELECT " +
      "d.id, " +
      "d.name, " +
      "d.nickname, " +
      "d.email, " +
      "d.phone, " +
      "d.cpf, " +
      "d.code, " +
      "d.user_id as userId FROM driver d " +
      "JOIN child c ON c.driver_id = :diverId " +
      "WHERE c.responsible_id = :responsibleId GROUP BY d.id;")
  DriverDAO findResponsibleDrivers(@Param("responsibleId") final long responsibleId, @Param("diverId") final long driverId);
}
