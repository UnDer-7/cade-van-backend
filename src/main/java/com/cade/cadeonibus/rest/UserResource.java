package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.UserRegisterDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import com.cade.cadeonibus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
  private final Logger log = LoggerFactory.getLogger(UserResource.class);
  private final UserService userService;


  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerAccount(@Valid @RequestBody UserRegisterDTO userRegisterDTO) throws Exception {
    log.info("Rest request to register user : {}", userRegisterDTO);
    userService.register(userRegisterDTO);
  }

  @GetMapping("/user-loggedin")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserResponseDTO> findUser() throws Exception{
    final UserResponseDTO dto = userService.findUser();
    return ResponseEntity.ok(dto);
  }
}
