package edu.kmaooad.capstone23.communication.interfaces.repositories;

import edu.kmaooad.capstone23.communication.dal.entities.Chat;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends PanacheMongoRepository<Chat> {
  Optional<Chat> findById(String id);

  Optional<Chat> findByName(String name);

  Chat insert(Chat chat);

  List<Chat> bulkInsert(List<Chat> chats);

  Boolean bulkDelete(List<Chat> chats);
}
