package edu.kmaooad.capstone23.communication.events;

import java.util.List;

public class ChatsBulkCreated {
  private final List<ChatCreated> createdChats;

  public ChatsBulkCreated(List<ChatCreated> createdChats) {
    this.createdChats = createdChats;
  }
}
