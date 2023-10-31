package edu.kmaooad.capstone23.communication.dal.repositories;

import edu.kmaooad.capstone23.communication.interfaces.ChatRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class ChatRepositoryImpl implements ChatRepository {
  @Override
  public Optional<Chat> findById(String id) {
    ObjectId parsedId = new ObjectId(id);

    return findByIdOptional(parsedId);
  }

  @Override
  public Optional<Chat> findByName(String name) {
    PanacheQuery<Chat> chat = find("name", name);

    return chat.firstResultOptional();
  }

  @Override
  public Chat insert(Chat chat) {
    persist(chat);

    return chat;
  }

  @Override
  public List<Chat> bulkInsert(List<Chat> chats) {
    persist(chats);

    return chats;
  }

  @Override
  public Boolean bulkDelete(List<Chat> chats) {
    List<Boolean> results = chats.stream().map(chat -> deleteById(chat.id)).toList();

    for (Boolean result : results) {
      if (!result) {
        return false;
      }
    }

    return true;
  }
}
