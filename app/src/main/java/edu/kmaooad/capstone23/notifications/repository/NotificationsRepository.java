package edu.kmaooad.capstone23.notifications.repository;

import edu.kmaooad.capstone23.mail.service.Notification;
import io.quarkus.panache.common.Parameters;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public class NotificationsRepository implements PanacheMongoRepository<Notification> {

    public Notification insert(Notification notification) {
        persist(notification);
        return notification;
    }

    public Notification findNotificationBy(String userId, String notificationType) {
        return find("userId = :userId and notificationType = :notificationType",
                Parameters.with("userId", userId).and("notificationType", notificationType))
                .firstResult();
    }
}
