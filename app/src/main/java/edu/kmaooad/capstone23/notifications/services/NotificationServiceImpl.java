package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {

    @Inject
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        notificationRepository.persist(notification);
        return notification;
    }

    @Override
    public Optional<Notification> findNotificationByUserIdAndType(String userId, String notificationType) {
        return Optional.ofNullable(notificationRepository.findNotificationBy(userId, notificationType));
    }
}
