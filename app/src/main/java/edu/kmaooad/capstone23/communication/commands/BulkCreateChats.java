package edu.kmaooad.capstone23.communication.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class BulkCreateChats {
  @NotEmpty
  private List<@Valid CreateChat> chats;

  public List<CreateChat> getChats() {
    return chats;
  }

  public void setChats(List<@Valid CreateChat> chats) {
    this.chats = chats;
  }
}
