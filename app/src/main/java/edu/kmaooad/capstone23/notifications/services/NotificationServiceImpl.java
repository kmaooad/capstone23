package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.dal.NotificationSendMethod;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class NotificationServiceImpl implements NotificationService {

    @Inject
    private NotificationRepository notificationsRepository;
    @Override
    public Notification createNotification(Notification notification) {
        return notificationsRepository.insert(notification);
    }

    @Override
    public void sendNotificationsByType(NotificationType notificationType) {
        var notifications = notificationsRepository.findByType(notificationType);
        for (var notification:
            notifications) {
            sendNotification(notification);
        }
    }

    private void sendNotification(Notification notification) {
        if (notification.notificationSendMethod == NotificationSendMethod.EMAIL) {
            //TODO: send by email
            return;
        }

        if (notification.notificationSendMethod == NotificationSendMethod.TEXT) {
            //TODO: send by sms
            return;
        }

        if (notification.notificationSendMethod == NotificationSendMethod.MESSENGER) {
            //TODO: send by messanger app
            return;
        }

        throw new Error("Unsupported notification message");
    }
}
