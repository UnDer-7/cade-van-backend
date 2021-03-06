package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.dto.*;
import com.cade.cadeonibus.dto.mapper.UserMapper;
import com.cade.cadeonibus.enums.Perfil;
import com.cade.cadeonibus.repository.UserRepository;
import com.cade.cadeonibus.security.SecurityUtils;
import com.cade.cadeonibus.service.DriverService;
import com.cade.cadeonibus.service.ResponsibleService;
import com.cade.cadeonibus.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  private final BCryptPasswordEncoder passwordEncoder;

  private final UserRepository userRepository;
  private final DriverService driverService;
  private final ResponsibleService responsibleService;

  private final UserMapper userMapper;

  @Override
  public UserResponseDTO findByLogin(String login) {
    final User user = userRepository.findByLogin(login).orElse(null);

    if (user.getPerfis().contains(Perfil.RESPONSIBLE)) {
      final ResponsibleDTO dto = responsibleService.findByEmail(user.getLogin());
      return new UserResponseDTO(dto);
    } else {
      final DriverDTO dto = driverService.findByEmail(user.getLogin());
      return new UserResponseDTO(dto);
    }
  }

  public UserResponseDTO findUser() {
    final String email = SecurityUtils.getCurrentUserLogin()
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não está logado"));

    final User user = userRepository.findByLogin(email)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

    if (user.getPerfis().contains(Perfil.RESPONSIBLE)) {
      final ResponsibleDTO responsible = responsibleService.findByEmail(user.getLogin());
      return new UserResponseDTO(responsible);
    }

    final DriverDTO driver = driverService.findByEmail(user.getLogin());
    return new UserResponseDTO(driver);
  }

  @Override
  public UserDTO findOne(Long id) {
    return userMapper.toDTO(
      userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " was not found in the database"))
    );
  }

  @Override
  public UserDTO save(UserDTO userDTO) {
    User user = userMapper.toEntity(userDTO);
    user = userRepository.save(user);
    return userMapper.toDTO(user);
  }

  @Override
  public void register(final UserRegisterDTO userRegisterDTO) {
    canRegister(userRegisterDTO);
    UserDTO userDTO = new UserDTO(userRegisterDTO, passwordEncoder.encode(userRegisterDTO.getPassword()));
    userDTO = save(userDTO);

    if (userRegisterDTO.getType() == Perfil.DRIVER) {
      DriverDTO driverDTO = new DriverDTO(userRegisterDTO, userDTO.getId());
      driverService.save(driverDTO);
    } else if (userRegisterDTO.getType() == Perfil.RESPONSIBLE) {
      ResponsibleDTO responsibleDTO = new ResponsibleDTO(userRegisterDTO, userDTO.getId());
      responsibleService.save(responsibleDTO);
    } else {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário  sem tipo");
    }
  }

  @Override
  public void updateToken(String deviceToken) {
    final String email = SecurityUtils.getCurrentUserLogin().
      orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não está logado"));

    User user = userRepository.findByLogin(email)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    user.setDeviceToken(deviceToken);
    userRepository.save(user);
  }

  private void canRegister(final UserRegisterDTO dto) {
    final User userFound = userRepository.findByLogin(dto.getEmail()).orElse(null);
    if (userFound != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado");
    }
  }
}
