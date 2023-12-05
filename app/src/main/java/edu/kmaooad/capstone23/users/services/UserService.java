package edu.kmaooad.capstone23.users.services;

import edu.kmaooad.capstone23.notifications.models.NotificationMethod;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
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

  public String setUserNotificationMethods(String userId, ArrayList<NotificationMethod> notificationMethods) {
    Optional<User> user = userRepository.findById(userId);
    if (user.isEmpty()) return null;
    user.get().notificationMethods = notificationMethods;
    userRepository.update(user.get());
    return user.get().id.toHexString();
  }
}
