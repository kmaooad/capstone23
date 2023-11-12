package edu.kmaooad.capstone23.communication.interfaces.services;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;

import java.util.List;

public interface ChatService {
  boolean isChatPresent(String id);

  Chat insert(Chat chat);

  List<Chat> bulkInsert(List<Chat> chats);

  boolean bulkDelete(List<Chat> chats);
}
