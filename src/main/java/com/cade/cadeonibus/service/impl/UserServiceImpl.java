package com.cade.cadeonibus.service.impl;

import com.cade.cadeonibus.domain.User;
import com.cade.cadeonibus.dto.DriverDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import com.cade.cadeonibus.dto.UserDTO;
import com.cade.cadeonibus.dto.UserRegisterDTO;
import com.cade.cadeonibus.dto.mapper.UserMapper;
import com.cade.cadeonibus.repository.UserRepository;
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
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final BCryptPasswordEncoder passwordEncoder;
  private final DriverService driverService;
  private final ResponsibleService responsibleService;

  @Override
  public UserDTO findByLogin(String login) {
    return userMapper.toDTO(
      userRepository.findByLogin(login)
        .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database")
        )
    );
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
  public void register(UserRegisterDTO userRegisterDTO) {
    UserDTO userDTO = new UserDTO(userRegisterDTO, passwordEncoder.encode(userRegisterDTO.getPassword()));
    userDTO = save(userDTO);

    if (userRegisterDTO.getType().equals("driver")) {
      DriverDTO driverDTO = new DriverDTO(userRegisterDTO, userDTO.getId());
      driverService.save(driverDTO);
    } else if (userRegisterDTO.getType().equals("responsible")) {
      ResponsibleDTO responsibleDTO = new ResponsibleDTO(userRegisterDTO, userDTO.getId());
      responsibleService.save(responsibleDTO);
    }
  }
}
