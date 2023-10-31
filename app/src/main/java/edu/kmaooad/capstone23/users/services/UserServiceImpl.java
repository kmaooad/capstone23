package edu.kmaooad.capstone23.users.services;

import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.interfaces.repositories.UserRepository;
import edu.kmaooad.capstone23.users.interfaces.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {
  @Inject
  UserRepository userRepository;

  public boolean isUserPresent(String id) {
    Optional<User> user = userRepository.findById(id);

    return user.isPresent();
  }
}
