package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.UserDTO;
import com.cade.cadeonibus.dto.UserRegisterDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;

public interface UserService {
  UserResponseDTO findByLogin(String login) throws Exception;

  UserDTO findOne(Long id);

  UserDTO save(UserDTO userDTO);

  void register(UserRegisterDTO userRegisterDTO) throws Exception;

  UserResponseDTO findUser() throws Exception;
}
