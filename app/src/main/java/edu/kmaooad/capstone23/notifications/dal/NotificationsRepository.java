package edu.kmaooad.capstone23.notifications.dal;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationsRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }

    public Notification findNotificationBy(String userId, String notificationStatus){
        return find(
                "userId = :userId and notificationStatus =:notificationStatus",
                Parameters.with("userId", userId).and("notificationStatus", notificationStatus)
        ).firstResult();
    }
}
