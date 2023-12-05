package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.notifications.models.NotificationMethod;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.inject.Inject;

public class NotificationServiceImpl implements NotificationService {
    @Inject
    UserService userService;

    @Inject
    EmailNotification emailNotification;

    @Inject
    SMSNotification smsNotification;

    @Inject
    TelegramNotification telegramNotification;

    @Override
    public void sendMessage(String userId, String message) {
        User user = userService.findUserById(userId);
        if (user == null) throw new IllegalArgumentException("User with id " + userId + " not found");
        if (user.notificationMethods == null) return;
        for (NotificationMethod notificationMethod : user.notificationMethods) {
            Notification notification = switch(notificationMethod) {
                case EMAIL -> emailNotification;
                case SMS -> smsNotification;
                case TELEGRAM -> telegramNotification;
            };
            notification.send(userId, message);
        }
    }
}
