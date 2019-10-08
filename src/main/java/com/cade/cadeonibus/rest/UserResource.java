package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.UserRegisterDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import com.cade.cadeonibus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
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
  public ResponseEntity<UserResponseDTO> findUserLoggedIn() throws Exception{
    final UserResponseDTO dto = userService.findUser();
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable final String email) throws Exception {
    final UserResponseDTO dto = userService.findByLogin(email);
    return ResponseEntity.ok(dto);
  }
}
