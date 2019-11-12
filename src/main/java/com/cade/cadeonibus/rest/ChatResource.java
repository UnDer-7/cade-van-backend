package com.cade.cadeonibus.rest;

import com.cade.cadeonibus.dto.ChatDTO;
import com.cade.cadeonibus.service.ChatService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatResource {
  private final Logger log = LoggerFactory.getLogger(ChatResource.class);

  private final ChatService chatService;
  private final UtilResponses<ChatDTO> utilResponses;

  @GetMapping
  public ResponseEntity<ChatDTO> getOne(@RequestParam("driverId") Long driverId,
                                        @RequestParam("responsibleId") Long responsibleId) {
    log.debug("REST request to get all chatMessages");
    ChatDTO chat = chatService.getOne(driverId, responsibleId);
    return utilResponses.successResponse(chat);
  }
}
