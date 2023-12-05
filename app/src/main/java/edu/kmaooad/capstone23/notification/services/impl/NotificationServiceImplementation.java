package edu.kmaooad.capstone23.notification.services.impl;
import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.dal.NotificationRepository;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import jakarta.inject.Inject;

public class NotificationServiceImplementation implements NotificationService {


    @Inject
    private NotificationRepository notificationsRepository;
    @Override
    public Notification insert(Notification notification) {
        return notificationsRepository.insert(notification);
    }


    @Override
    public Notification findNotificationBy(String userId, String notificationType) {
        return notificationsRepository.findNotificationBy(userId,notificationType);
    }

}