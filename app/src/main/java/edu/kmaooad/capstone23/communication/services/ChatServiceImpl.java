package edu.kmaooad.capstone23.communication.services;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import edu.kmaooad.capstone23.communication.dal.repositories.ChatRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ChatServiceImpl implements ChatService {
  @Inject
  ChatRepository chatRepository;

  @Override
  public boolean isChatPresent(String id) {
    Optional<Chat> chat = chatRepository.findById(id);

    return chat.isPresent();
  }

  @Override
  public Chat insert(Chat chat) {
    return chatRepository.insert(chat);
  }

  @Override
  public List<Chat> bulkInsert(List<Chat> chats) {
    return chatRepository.bulkInsert(chats);
  }

  @Override
  public Boolean bulkDelete(List<Chat> chats) {
    return chatRepository.bulkDelete(chats);
  }
}
