package edu.kmaooad.capstone23.notifications.services.provider;

import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.notifications.services.NotificationProvider;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 * EmailNotificationService
 */
@ApplicationScoped
public class EmailNotificationService implements NotificationProvider {
  @Inject
  private UserService userService;

  @Override
  public void send(NotificationType type, ObjectId userId, String message) {
    var email = getUserEmail(userId);

    if (email.isEmpty()) {
      return;
    }

    System.out.println("Sending email to: " + email +
        " with message: " + message);
  }

  private Optional<String> getUserEmail(ObjectId userId) {
    var user = userService.getUser(userId);

    if (user.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(user.get().email);
  }
}
