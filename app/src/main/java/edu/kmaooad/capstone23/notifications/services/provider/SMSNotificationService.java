package edu.kmaooad.capstone23.notifications.services.provider;

import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.notifications.services.NotificationProvider;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import org.bson.types.ObjectId;

@ApplicationScoped
public class SMSNotificationService implements NotificationProvider {
  @Inject
  private UserService userService;

  @Override
  public void send(NotificationType type, ObjectId userId, String message) {
    var phoneNumber = getUserPhoneNumber(userId);

    if (phoneNumber.isEmpty()) {
      return;
    }

    System.out.println("Sending SMS to: " + phoneNumber +
        " with message: " + message);
  }

  private Optional<String> getUserPhoneNumber(ObjectId userId) {
    var user = userService.getUser(userId);

    if (user.isEmpty()) {
      return Optional.empty();
    }

    var phoneNumber = user.get().phoneNumber;

    if (phoneNumber == null) {
      return Optional.empty();
    }

    return Optional.of(phoneNumber);
  }
}
