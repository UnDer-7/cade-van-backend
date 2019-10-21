package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Chat;
import com.cade.cadeonibus.dto.ChatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DriverMapper.class, ResponsibleMapper.class, ChatMessageMapper.class})
public interface ChatMapper extends EntityMapper<ChatDTO, Chat> {

  @Mapping(source = "driverId", target = "driver")
  @Mapping(source = "responsibleId", target = "responsible")
  Chat toEntity(ChatDTO dto);

  @Mapping(source = "driver.id", target = "driverId")
  @Mapping(source = "responsible.id", target = "responsibleId")
  @Mapping(source = "messages", target = "messages")
  ChatDTO toDTO(Chat entity);

  default Chat fromId(Long id) {
    if (id == null) {
      return null;
    }
    Chat chat = new Chat();
    chat.setId(id);
    return chat;
  }
}
