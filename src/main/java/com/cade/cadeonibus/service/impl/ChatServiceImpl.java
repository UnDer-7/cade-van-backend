package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Chat;
import com.cade.cadeonibus.domain.ChatMessage;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ChatDTO;
import com.cade.cadeonibus.dto.ChatMessageDTO;
import com.cade.cadeonibus.dto.mapper.ChatMapper;
import com.cade.cadeonibus.dto.mapper.ChatMessageMapper;
import com.cade.cadeonibus.dto.mapper.DriverMapper;
import com.cade.cadeonibus.dto.mapper.ResponsibleMapper;
import com.cade.cadeonibus.repository.ChatMessageRepository;
import com.cade.cadeonibus.repository.ChatRepository;
import com.cade.cadeonibus.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatRepository chatRepository;
  private final ChatMapper chatMapper;
  private final ChatMessageRepository chatMessageRepository;
  private final ChatMessageMapper chatMessageMapper;
  private final DriverMapper driverMapper;
  private final ResponsibleMapper responsibleMapper;

  @Override
  public ChatDTO getOne(Long driverId, Long responsibleId) {
    Chat chat = chatRepository.findOneByDriverIdAndResponsibleId(driverId, responsibleId);
    if (chat == null) {
      Driver driver = driverMapper.fromId(driverId);
      Responsible responsible = responsibleMapper.fromId(responsibleId);
      chat = chatRepository.save(new Chat(driver, responsible, null));
    }
    return chatMapper.toDTO(chat);
  }

  @Override
  public ChatMessage saveMessage(ChatMessageDTO chatMessageDTO) {
    ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
    return chatMessageRepository.save(chatMessage);
  }
}
