package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

  List<Child> findAllByDriverEmail(String email);

  List<Child> findAllByResponsibleEmail(String email);
}
