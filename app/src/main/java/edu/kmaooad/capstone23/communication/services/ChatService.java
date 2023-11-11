package edu.kmaooad.capstone23.communication.services;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

public interface ChatService {

  boolean isChatPresent(String id);

  Chat insert(Chat chat);

  List<Chat> bulkInsert(List<Chat> chats);
  
  Boolean bulkDelete(List<Chat> chats);
}
