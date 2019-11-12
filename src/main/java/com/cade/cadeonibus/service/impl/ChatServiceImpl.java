package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Chat;
import com.cade.cadeonibus.domain.ChatMessage;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.*;
import com.cade.cadeonibus.dto.mapper.ChatMapper;
import com.cade.cadeonibus.dto.mapper.ChatMessageMapper;
import com.cade.cadeonibus.dto.mapper.DriverMapper;
import com.cade.cadeonibus.dto.mapper.ResponsibleMapper;
import com.cade.cadeonibus.enums.Perfil;
import com.cade.cadeonibus.repository.ChatMessageRepository;
import com.cade.cadeonibus.repository.ChatRepository;
import com.cade.cadeonibus.service.ChatService;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.ResponsibleService;
import com.cade.cadeonibus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatRepository chatRepository;
  private final ChatMapper chatMapper;
  private final ChatMessageRepository chatMessageRepository;
  private final ChatMessageMapper chatMessageMapper;
  private final DriverService driverService;
  private final DriverMapper driverMapper;
  private final ResponsibleService responsibleService;
  private final ResponsibleMapper responsibleMapper;
  private final UserService userService;

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
  public List<ChatDTO> findAll() throws Exception {
    UserResponseDTO user = userService.findUser();
    if (user.getPerfil().equals(Perfil.RESPONSIBLE)) {
      ResponsibleDTO responsible = responsibleService.findByEmail(user.getEmail());
      return chatMapper.toDTO(chatRepository.findAllByResponsibleId(responsible.getId()));
    } else {
      DriverDTO driver = driverService.findByEmail(user.getEmail());
      return chatMapper.toDTO(chatRepository.findAllByDriverId(driver.getId()));
    }
  }

  @Override
  public ChatDTO getOneByChatIdAndResponsibleId(Long chatId, Long responsibleId) {
    Chat chat = chatRepository.findOneByIdAndResponsibleId(chatId, responsibleId);
    return chatMapper.toDTO(chat);
  }

  @Override
  public ChatDTO getOneByChatIAndDriverId(Long chatId, Long driverId) {
    Chat chat = chatRepository.findOneByIdAndDriverId(chatId, driverId);
    return chatMapper.toDTO(chat);
  }

  @Override
  public ChatMessage saveMessage(ChatMessageDTO chatMessageDTO) {
    ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
    return chatMessageRepository.save(chatMessage);
  }
}
