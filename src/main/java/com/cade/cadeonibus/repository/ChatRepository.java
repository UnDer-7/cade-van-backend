package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
  Chat findOneByDriverIdAndResponsibleId(Long driverId, Long responsibleId);
}
