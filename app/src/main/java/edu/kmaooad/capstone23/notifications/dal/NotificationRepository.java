package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }

    public Notification findNotificationByIdAndType(String userId, String eventType){
        return find("userId = :userId and eventType = :eventType",
                Parameters.with("userId", userId).and("eventType", eventType)).firstResult();
    }
}
