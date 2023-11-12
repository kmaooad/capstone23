package edu.kmaooad.capstone23.users.services;

import com.mongodb.client.model.IndexOptions;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.interfaces.repositories.UserRepository;
import edu.kmaooad.capstone23.users.interfaces.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {
  @Inject
  UserRepository userRepository;

  @Override
  public boolean isUserPresent(String id) {
    Optional<User> user = userRepository.findById(id);

    return user.isPresent();
  }

  @Override
  public Optional<User> findById(String id) {
    return userRepository.findById(id);
  }

  @Override
  public User getById(String id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User insert(User user) {
    return userRepository.insert(user);
  }

  @Override
  public void deleteByEmail(String email) {
    userRepository.deleteByEmail(email);
  }

  @Override
  public void deleteAll() {
    userRepository.deleteAll();
  }

  @Override
  public void createIndex(Document index, IndexOptions options) {
    userRepository.createIndex(index, options);
  }
}
