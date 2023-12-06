package edu.kmaooad.capstone23.notifications.dal;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.panache.common.Parameters;
@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }

    public Notification findNotificationBy(String userId, String notificationType){
        return find("userId = :userId and notificationType =:notificationType",
                Parameters.with("userId", userId).and("notificationType", notificationType))
                .firstResult();
    }
}
