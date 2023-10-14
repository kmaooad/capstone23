package edu.kmaooad.capstone23.communication.dal.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class ChatRepository implements PanacheMongoRepository<Chat> {
  public Optional<Chat> findById(String id) {
    ObjectId parsedId = new ObjectId(id);

    return findByIdOptional(parsedId);
  }

  public Optional<Chat> findByName(String name) {
    PanacheQuery<Chat> chat = find("name", name);

    return chat.firstResultOptional();
  }

  public Chat insert(Chat chat) {
    persist(chat);

    return chat;
  }

  public List<Chat> bulkInsert(List<Chat> chats) {
    persist(chats);

    return chats;
  }

  public List<Chat> bulkUpdate(List<Chat> chatsToUpdate) {
    chatsToUpdate.forEach(this::updateChat);
    return chatsToUpdate;
  }

  private void updateChat(Chat chatToUpdate) {
    Optional<Chat> existingChatOpt = findById(chatToUpdate.id.toHexString());

    if (existingChatOpt.isPresent()) {
      Chat existingChat = existingChatOpt.get();

      // Update fields of the existing chat from the provided chatToUpdate
      existingChat.name = chatToUpdate.name;
      existingChat.description = chatToUpdate.description;
      existingChat.accessType = chatToUpdate.accessType;

      // Persist the changes
      persistOrUpdate(existingChat);
    }
  }
}
