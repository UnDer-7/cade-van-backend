package com.cade.cadeonibus.service;

import com.cade.cadeonibus.domain.ChatMessage;
import com.cade.cadeonibus.dto.ChatDTO;
import com.cade.cadeonibus.dto.ChatMessageDTO;

import java.util.List;

public interface ChatService {
  ChatDTO getOne(Long driverId, Long responsibleId);

  List<ChatDTO> findAll() throws Exception;

  ChatDTO getOneByChatIdAndResponsibleId(Long chatId, Long responsibleId);

  ChatDTO getOneByChatIAndDriverId(Long chatId, Long driverId);

  ChatMessage saveMessage(ChatMessageDTO chatMessageDTO);
}
