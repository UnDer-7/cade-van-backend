package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
}
