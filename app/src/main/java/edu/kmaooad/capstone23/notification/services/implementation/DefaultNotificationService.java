package edu.kmaooad.capstone23.notification.services.implementation;

import edu.kmaooad.capstone23.notification.dal.Notification;
import edu.kmaooad.capstone23.notification.dal.NotificationsRepository;
import edu.kmaooad.capstone23.notification.services.NotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@ApplicationScoped
public class DefaultNotificationService implements NotificationService {
    @Inject
    NotificationsRepository notificationsRepository;
    @Override
    public Notification insert(Notification notification) {
        return notificationsRepository.insert(notification);
    }

    @Override
    public Notification findNotificationBy(ObjectId userId, String notificationType) {
        return notificationsRepository.findNotificationBy(userId, notificationType);
    }
}
