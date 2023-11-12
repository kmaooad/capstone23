package edu.kmaooad.capstone23.users.interfaces.services;

import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import edu.kmaooad.capstone23.users.dal.entities.User;
import jakarta.validation.constraints.Email;

import java.util.Optional;

public interface UserService {
  boolean isUserPresent(String id);

  Optional<User> findById(String id);

  User getById(String id);

  Optional<User> findByEmail(@Email String email);

  User insert(User user);

  void deleteByEmail(String email);

  void deleteAll();

  void createIndex(Document index, IndexOptions options);
}
