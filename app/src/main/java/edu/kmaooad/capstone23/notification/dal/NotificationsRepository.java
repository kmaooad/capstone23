package edu.kmaooad.capstone23.notification.dal;


import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class NotificationsRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }

    public Notification findNotificationBy(ObjectId userId, String notificationType){
        return find("userId = :userId and notificationType =:notificationType",
                Parameters.with("userId", userId).and("notificationType", notificationType))
                .firstResult();
    }
}
