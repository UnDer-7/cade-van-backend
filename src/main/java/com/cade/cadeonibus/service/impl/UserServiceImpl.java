package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.UserDTO;
import com.cade.cadeonibus.dto.UserRegisterDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
  public UserResponseDTO findByLogin(String login) throws Exception {
    final User user = userRepository.findByLogin(login).orElse(null);
    if (user == null) {
      throw new Exception("Usuario com E-mail: " + login + " nao encontrado");
    }

    if (user.getPerfis().contains(Perfil.RESPONSIBLE)) {
      final ResponsibleDTO dto = responsibleService.findByEmail(user.getLogin());
      return new UserResponseDTO(dto);
    }

    if (user.getPerfis().contains(Perfil.DRIVER)) {
      final DriverDTO dto = driverService.findByEmail(user.getLogin());
      return new UserResponseDTO(dto);
    }
    throw new Exception("Usuario com Perfil: " + user.getPerfis() + " nao encontrado");
  }

  public UserResponseDTO findUser() throws Exception {
    final String email = SecurityUtils.getCurrentUserLogin().orElse(null);

    if (email == null) {
      throw new Exception("Nenhum usuario logado");
    }

    final User user = userRepository.findByLogin(email).orElse(null);

    if (user == null) {
      throw new Exception("Usuario nao encontrado!");
    }

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
  public void register(UserRegisterDTO userRegisterDTO) throws Exception {
    UserDTO userDTO = new UserDTO(userRegisterDTO, passwordEncoder.encode(userRegisterDTO.getPassword()));
    userDTO = save(userDTO);

    if (userRegisterDTO.getType() == Perfil.DRIVER) {
      DriverDTO driverDTO = new DriverDTO(userRegisterDTO, userDTO.getId());
      driverService.save(driverDTO);
    } else if (userRegisterDTO.getType() == Perfil.RESPONSIBLE) {
      ResponsibleDTO responsibleDTO = new ResponsibleDTO(userRegisterDTO, userDTO.getId());
      responsibleService.save(responsibleDTO);
    } else {
      throw new Exception("Usuario sem Tipo");
    }
  }
}
