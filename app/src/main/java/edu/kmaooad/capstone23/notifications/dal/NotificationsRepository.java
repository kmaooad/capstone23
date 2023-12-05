package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class NotificationsRepository implements PanacheMongoRepository<Notification> {

    public Notification insert(Notification notification) {
        var persistedNotification = findByUserIdAndTypeOptional(notification);
        if (persistedNotification.isPresent())
            return persistedNotification.get();

        persist(notification);
        return notification;
    }

    public void remove(Notification notification) {
        var persistedNotification = findByUserIdAndTypeOptional(notification);
        if (persistedNotification.isPresent())
            delete(persistedNotification.get());
    }

    public Optional<Notification> findByUserIdAndTypeOptional(Notification notification) {
        var document = new Document("userId", notification.userId)
                .append("messageType", notification.notificationType);
        return find(document).firstResultOptional();
    }

    public List<Notification> getNotificationsByType(String notificationType) {
        return find("notificationType", notificationType)
                .list();
    }
}
