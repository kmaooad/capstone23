package edu.kmaooad.capstone23.users.services;

import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UserService {
  @Inject
  UserRepository userRepository;

  public boolean isUserPresent(String id) {
    Optional<User> user = userRepository.findById(id);

    return user.isPresent();
  }

  public User findUserById(String id) {
    return userRepository.findById(id).orElse(null);
  }
}
