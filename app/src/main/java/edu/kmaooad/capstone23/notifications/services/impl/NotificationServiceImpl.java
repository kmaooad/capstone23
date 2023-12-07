package edu.kmaooad.capstone23.notifications.services.impl;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.inject.Inject;

public class NotificationServiceImpl implements NotificationService {
    @Inject
    private NotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification findByUserIdAndType(String userId, String type) {
        return notificationRepository.findByUserIdAndType(userId, type);
    }
}
