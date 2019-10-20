package com.cade.cadeonibus.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
public class ChatMessage extends BaseAbstract {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "chat_id", nullable = false)
  private Chat chat;

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

}
