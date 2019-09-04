package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.UserRegisterDTO;
import com.cade.cadeonibus.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserResource {
  private final Logger log = LoggerFactory.getLogger(UserResource.class);
  private final UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerAccount(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
    log.info("Rest request to register user : {}", userRegisterDTO);
    userService.register(userRegisterDTO);
  }
}
