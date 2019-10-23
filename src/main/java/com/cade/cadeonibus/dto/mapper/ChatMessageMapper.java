package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.ChatMessage;
import com.cade.cadeonibus.dto.ChatMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ChatMapper.class, UserMapper.class})
public interface ChatMessageMapper extends EntityMapper<ChatMessageDTO, ChatMessage> {

  @Mapping(source = "chatId", target = "chat")
  @Mapping(source = "userId", target = "user")
  ChatMessage toEntity(ChatMessageDTO dto);

  @Mapping(source = "chat.id", target = "chatId")
  @Mapping(source = "user.id", target = "userId")
  ChatMessageDTO toDTO(ChatMessage entity);

  default ChatMessage fromId(Long id) {
    if (id == null) {
      return null;
    }
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setId(id);
    return chatMessage;
  }
}
