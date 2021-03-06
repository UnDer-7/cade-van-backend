package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.UserRegisterDTO;
import com.cade.cadeonibus.dto.UserResponseDTO;
import com.cade.cadeonibus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResource {
  private final Logger log = LoggerFactory.getLogger(UserResource.class);
  private final UserService userService;


  @PostMapping("/register")
  public void registerAccount(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
    log.info("Rest request to register user : {}", userRegisterDTO);
    userService.register(userRegisterDTO);
  }

  @GetMapping("/user-loggedin")
  public ResponseEntity<UserResponseDTO> findUserLoggedIn() {
    final UserResponseDTO dto = userService.findUser();
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable final String email) {
    final UserResponseDTO dto = userService.findByLogin(email);
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/device-token")
  public void updateToken(@RequestBody String deviceToken) throws JSONException {
    final String token = new JSONObject(deviceToken)
      .get("deviceToken")
      .toString();
    userService.updateToken(token);
  }
}
