package edu.kmaooad.capstone23.communication.services;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.interfaces.repositories.ChatRepository;
import edu.kmaooad.capstone23.communication.interfaces.services.ChatService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ChatServiceImpl implements ChatService {
  @Inject
  ChatRepository chatRepository;

  public boolean isChatPresent(String id) {
    Optional<Chat> chat = chatRepository.findById(id);

    return chat.isPresent();
  }

  public Chat insert(Chat chat) {
    return chatRepository.insert(chat);
  }

  public List<Chat> bulkInsert(List<Chat> chats) {
    return chatRepository.bulkInsert(chats);
  }

  public boolean bulkDelete(List<Chat> chats) {
    return chatRepository.bulkDelete(chats);
  }
}
