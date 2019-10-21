package com.cade.cadeonibus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat extends BaseAbstract {

  @ManyToOne
  private Driver driver;

  @ManyToOne
  private Responsible responsible;

  @JsonIgnoreProperties("chat")
  @OneToMany(mappedBy = "chat")
  private List<ChatMessage> messages;
}
