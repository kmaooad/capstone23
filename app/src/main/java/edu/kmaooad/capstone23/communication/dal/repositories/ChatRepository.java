package edu.kmaooad.capstone23.communication.dal.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import org.bson.types.ObjectId;

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
}
