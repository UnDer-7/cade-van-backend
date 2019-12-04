package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Chat;
import com.cade.cadeonibus.domain.ChatMessage;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ChatDTO;
import com.cade.cadeonibus.dto.ChatMessageDTO;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.UserDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import com.cade.cadeonibus.dto.mapper.ChatMapper;
import com.cade.cadeonibus.dto.mapper.ChatMessageMapper;
import com.cade.cadeonibus.dto.mapper.DriverMapper;
import com.cade.cadeonibus.dto.mapper.ResponsibleMapper;
import com.cade.cadeonibus.enums.Perfil;
import com.cade.cadeonibus.repository.ChatMessageRepository;
import com.cade.cadeonibus.repository.ChatRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.repository.ResponsibleRepository;
import com.cade.cadeonibus.service.ChatService;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.NotificationService;
import com.cade.cadeonibus.service.ResponsibleService;
import com.cade.cadeonibus.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
  private final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

  private final ChatRepository chatRepository;
  private final ChatMessageRepository chatMessageRepository;
  private final ResponsibleRepository responsibleRepository;
  private final DriverRepository driverRepository;

  private final NotificationService notificationService;
  private final DriverService driverService;
  private final ResponsibleService responsibleService;
  private final UserService userService;

  private final ChatMessageMapper chatMessageMapper;
  private final ChatMapper chatMapper;
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
  public List<ChatDTO> findAll() {
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
  public void sendNotification(final ChatMessageDTO chatMessage) {
    final UserDTO user = userService.findOne(chatMessage.getUserId());
    logger.debug("Starting to send Message: {}", chatMessage);

    if (user.getPerfis().contains(Perfil.RESPONSIBLE)) {
      handleResponsibleNotification(user, chatMessage);
    } else {
      handleDriverNotification(user, chatMessage);
    }
  }

  @Override
  public List<Long> getAllChatIds(Long userId) {
    return findAll()
      .stream()
      .map(ChatDTO::getId)
      .collect(Collectors.toList());
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
  public void saveMessage(ChatMessageDTO chatMessageDTO) {
    ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
    chatMessageRepository.save(chatMessage);
  }

  private void handleResponsibleNotification(final UserDTO user, final ChatMessageDTO chatMessage) {
    final ResponsibleDTO responsible = responsibleService.findByEmail(user.getLogin());
    final ChatDTO chat = getOneByChatIdAndResponsibleId(chatMessage.getChatId(), responsible.getId());
    final UserResponseDTO completeUser = userService.findByLogin(user.getLogin());
    final String token = driverRepository
      .getOne(chat.getDriverId())
      .getUser()
      .getDeviceToken();

    notificationService.sendNotification(completeUser.getName(), chatMessage.getText(), token);
    logger.debug("Message sent to Responsible {}\tMessage: {}", completeUser.getName(), chatMessage.getText());
  }

  private void handleDriverNotification(final UserDTO user, final ChatMessageDTO chatMessage) {
    DriverDTO driver = driverService.findByEmail(user.getLogin());
    ChatDTO chat = getOneByChatIAndDriverId(chatMessage.getChatId(), driver.getId());
    final UserResponseDTO completeUser = userService.findByLogin(user.getLogin());
    final String token = responsibleRepository.getOne(chat.getResponsibleId())
      .getUser()
      .getDeviceToken();

    notificationService.sendNotification(completeUser.getName(), chatMessage.getText(), token);
    logger.debug("Message sent to Driver {}\tMessage: {}", completeUser.getName(), chatMessage.getText());
  }
}
