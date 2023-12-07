package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.Optional;

@ApplicationScoped
public class NotificationTypeRepository implements PanacheMongoRepository<NotificationType> {
    public NotificationType insert(NotificationType notificationType) {
        persist(notificationType);
        return notificationType;
    }

    public Optional<NotificationType> findByUserAndType(String userId, String notificationType) {
        var document = new Document("userId", userId).append("notificationType", notificationType);
        return find(document).firstResultOptional();
    }
}
