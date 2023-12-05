package edu.kmaooad.capstone23.notification.dal;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

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