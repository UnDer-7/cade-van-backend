package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.domain.Driver;
import com.cade.cadeonibus.domain.ItineraryChild;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.UserDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import com.cade.cadeonibus.dto.mapper.ChildMapper;
import com.cade.cadeonibus.enums.ChildStatus;
import com.cade.cadeonibus.enums.Perfil;
import com.cade.cadeonibus.repository.ChildRepository;
import com.cade.cadeonibus.repository.DriverRepository;
import com.cade.cadeonibus.repository.ItineraryChildRepository;
import com.cade.cadeonibus.repository.ResponsibleRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.ChildService;
import com.cade.cadeonibus.service.UserService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
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
public class ChildServiceImpl implements ChildService {
  private final Logger log = LoggerFactory.getLogger(ChildServiceImpl.class);

  private final ChildRepository childRepository;
  private final DriverRepository driverRepository;
  private final ResponsibleRepository responsibleRepository;
  private final ItineraryChildRepository itineraryChildRepository;

  private final UserService userService;

  private final ChildMapper childMapper;

  @Override
  public List<ChildDTO> findAll() {
    log.debug("Request to get all children");

    final String login = SecurityUtils.getCurrentUserLogin().orElse(null);

    final UserResponseDTO user = userService.findByLogin(login);

    if (user.getPerfil() == Perfil.DRIVER) {
      final List<Child> children = childRepository.findAllByDriverEmail(user.getEmail());
      return childMapper.toDTO(children);
    }
    final List<Child> children = childRepository.findAllByResponsibleEmail(user.getEmail());
    final List<ChildDTO> dtos = childMapper.toDTO(children);
    return dtos;
  }

  @Override
  public ChildDTO getOne(Long id) {
    log.debug("Request to get Child -> {}", id);

    Child child = childRepository.getOne(id);
    return childMapper.toDTO(child);
  }

  @Override
  public void save(ChildDTO dto) {
    log.debug("Request to save Child -> {}", dto);

    final Driver driver = driverRepository.findByCode(dto.getDriverCode());
    final String email = SecurityUtils.getCurrentUserLogin().orElse(null);
    final Responsible responsible = responsibleRepository.findByEmail(email);
    final Child child = childMapper.toEntity(dto);

    child.setResponsible(responsible);
    child.setDriver(driver);
    child.setStatus(ChildStatus.LEFT_HOME);
    childRepository.save(child);
  }

  @Override
  public ChildDTO update(final ChildDTO childDTO) {
    final Child child = childMapper.toEntity(childDTO);
    final Child childSaved = childRepository.save(child);

    try {
      sendNotification(childSaved);
    } catch (Exception e) {
      e.printStackTrace();
    }

    final ChildDTO dto = childMapper.toDTO(childSaved);
    return dto;
  }

  private void sendNotification(Child child) {
    UserResponseDTO user = userService.findByLogin(child.getDriver().getEmail());
    UserDTO userDTO = userService.findOne(user.getUserId());

    Notification notification = new Notification(child.getName(), getNotificationBody(child.getStatus()));

    Message message = Message.builder()
      .setNotification(notification)
      .setToken(userDTO.getDeviceToken())
      .build();

    try {
      String response = FirebaseMessaging.getInstance().send(message);
      log.info("Message sent {}", response);
    } catch (FirebaseMessagingException e) {
      log.error("Não foi possível enviar a notificação de atualização de status da criança {}", message);
      e.printStackTrace();
    }
  }

  private String getNotificationBody(ChildStatus status) {
    String body = null;
    switch (status) {
      case WAITING:
        body = "O motorista acabou de iniciar o itinerário e está a caminho.";
        break;
      case GOING_HOME:
        body = "O motorista está a caminho da sua casa.";
        break;
      case LEFT_HOME:
        body = "Acabou de chegar em casa.";
        break;
      case GOING_SCHOOL:
        body = "O motorista está a caminho do colégio.";
        break;
      case LEFT_SCHOOL:
        body = "Acabou de chegar ao colégio.";
        break;
    }
    return body;
  }

  @Override
  public void updateStatusToWaiting(final long itineraryId) {
    itineraryChildRepository.findAllByItineraryId(itineraryId).stream()
      .map(ItineraryChild::getChild)
      .peek(item -> item.setStatus(ChildStatus.WAITING))
      .forEach(childRepository::save);
  }
}
