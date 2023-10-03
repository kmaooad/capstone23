package edu.kmaooad.capstone23.communication.utils;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;

import java.util.List;

public class ChatsListWrapper {
  private final List<Chat> chats;

  public ChatsListWrapper(List<Chat> chats) {
    this.chats = chats;
  }

  public List<Chat> getChats() {
    return chats;
  }
}
