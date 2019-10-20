package com.cade.cadeonibus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat extends BaseAbstract {

  @JsonIgnoreProperties("chat")
  @OneToMany(mappedBy = "chat")
  private Set<ChatUser> chatUsers;

  @JsonIgnoreProperties("chat")
  @OneToMany(mappedBy = "chat")
  private List<ChatMessage> chatMessages;
}
