package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification save(Notification notification) {
        persist(notification);
        return notification;
    }

    public Notification findByUserIdAndType(String userId, String type){
        return find("userId = :userId and type =:type",
                Parameters.with("userId", userId).and("notificationType", type))
                .firstResult();
    }
}
