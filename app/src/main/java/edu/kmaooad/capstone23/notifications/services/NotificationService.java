package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.models.Event;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import edu.kmaooad.capstone23.users.dal.entities.User;
import edu.kmaooad.capstone23.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationService implements INotificationService {

    @Inject
    UserService userService;

    @Inject
    EmailService emailService;

    @Inject
    SMSService smsService;

    @Inject
    TGService tgService;

     public boolean sendNotification(String userId, Event event, String message) {
        User user = userService.getUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (user.notificationTypes == null) {
           return false;
        }
        for (NotificationType notificationType : user.notificationTypes) {
            switch (notificationType) {
                case EMAIL:
                    emailService.sendEmail(user.email, event, message);
                    break;
                case SMS:
                    smsService.sendSMS(user.phoneNumber, event, message);
                    break;
                case TG:
                    tgService.sendTG(user.phoneNumber, event, message);
                    break;
            }
        }
        return true;
    }
}
