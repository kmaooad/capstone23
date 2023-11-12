package edu.kmaooad.capstone23.users.interfaces.repositories;

import com.mongodb.client.model.IndexOptions;
import edu.kmaooad.capstone23.users.dal.entities.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.validation.constraints.Email;
import org.bson.Document;

import java.util.Optional;

public interface UserRepository extends PanacheMongoRepository<User> {
  Optional<User> findById(String id);

  Optional<User> findByEmail(@Email String email);

  User insert(User user);

  void deleteByEmail (String email);

  void createIndex(Document index, IndexOptions options);
}
