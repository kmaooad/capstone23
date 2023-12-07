package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PushNotificationRepository implements PanacheMongoRepository<PushNotification> {
    public PushNotification insert(PushNotification notification){
        persist(notification);
        return notification;
    }

    public PushNotification findNotificationBy(
            String userId,
            String notificationEvent
    ) {
        return find("userId = :userId and notificationEvent =:notificationEvent",
                Parameters.with("userId", userId).and("notificationEvent", notificationEvent))
                .firstResult();
    }
}
