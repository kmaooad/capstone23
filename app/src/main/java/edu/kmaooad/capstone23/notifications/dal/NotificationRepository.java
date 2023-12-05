package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification) {
        persist(notification);
        return notification;
    }

    public List<Notification> findByType(NotificationType notificationType) {
        return find("notificationType", notificationType).stream().toList();
    }
}
