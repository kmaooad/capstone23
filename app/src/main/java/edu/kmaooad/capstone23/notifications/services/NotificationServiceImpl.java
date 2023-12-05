package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import jakarta.inject.Inject;

public class NotificationServiceImpl implements NotificationService {

    @Inject
    private NotificationRepository notificationRepository;

    @Override
    public Notification insert(Notification notification) {
        return notificationRepository.insert(notification);
    }

    @Override
    public Notification findNotificationByIdAndType(String userId, String eventType) {
        return notificationRepository.findNotificationByIdAndType(userId, eventType);
    }
}
