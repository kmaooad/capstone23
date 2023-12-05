package edu.kmaooad.capstone23.users.services;

import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.vertx.core.cli.Option;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

import org.bson.types.ObjectId;

@ApplicationScoped
public class UserService {
  @Inject
  UserRepository userRepository;

  public boolean isUserPresent(String id) {
    Optional<User> user = userRepository.findById(id);

    return user.isPresent();
  }

  public Optional<User> getUser(ObjectId id) {
    Optional<User> user = userRepository.findByIdOptional(id);

    return user;
  }
}
