package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;

import java.util.Optional;

public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification) {
        persist(notification);
        return notification;
    }

    public Optional<Notification> findById(String id, NotificationType notificationType) {
        try {
            return find("userId =:id and notificationType =:notificationType",
                    Parameters.with("id", id).and("notificationType", notificationType)).firstResultOptional();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
