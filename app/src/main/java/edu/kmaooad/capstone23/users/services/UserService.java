package edu.kmaooad.capstone23.users.services;

import edu.kmaooad.capstone23.notifications.models.NotificationType;
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

  public User getUser(String id) {
    Optional<User> user = userRepository.findById(id);

    return user.orElse(null);
  }

    public User updateUserNotificationPreferences(String userId, ArrayList<NotificationType> notificationTypes) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().notificationTypes = notificationTypes;
            userRepository.update(user.get());
            return user.get();
        }
        return null;
    }
}
