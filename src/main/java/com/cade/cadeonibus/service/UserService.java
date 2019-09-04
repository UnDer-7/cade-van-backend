package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.UserDTO;
import com.cade.cadeonibus.dto.UserRegisterDTO;

public interface UserService {
  UserDTO findByLogin(String login);

  UserDTO findOne(Long id);

  UserDTO save(UserDTO userDTO);

  void register(UserRegisterDTO userRegisterDTO);
}
