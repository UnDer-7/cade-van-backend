package com.cade.cadeonibus.repository;

import com.cade.cadeonibus.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
  Chat findOneByDriverIdAndResponsibleId(Long driverId, Long responsibleId);

  Chat findOneByIdAndResponsibleId(Long chatId, Long responsibleId);

  Chat findOneByIdAndDriverId(Long chatId, Long driverId);

  List<Chat> findAllByDriverId(Long driverId);

  List<Chat> findAllByResponsibleId(Long responsibleId);
}
