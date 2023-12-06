package edu.kmaooad.capstone23.notifications.dal;

import java.util.List;
import java.util.Optional;
import org.bson.Document;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserNotificationsRepository implements PanacheMongoRepository<UserNotification> {

    public UserNotification insert(UserNotification notification){
        var userNotification = findByUserIdAndTypeOptional(notification);
        if(userNotification.isPresent())
            return userNotification.get();
        
        persist(notification);
        return notification;
    }

    public UserNotification findByUserIdAndType(String userId, String notificationType){
        return find("userId = :userId and notificationType =:notificationType",
                Parameters.with("userId", userId).and("notificationType", notificationType))
                .firstResult();
    }

    public Optional<UserNotification> findByUserIdAndTypeOptional(UserNotification notification) {
        var document = new Document("userId", notification.userId).append("messageType", notification.notificationType);
        return find(document).firstResultOptional();
    }

    public boolean isExist(UserNotification notification) {
        var document = new Document("userId", notification.userId).append("messageType", notification.notificationType);
        return find(document).firstResultOptional().isPresent();
    }

    public void remove(UserNotification notification) {
        var userNotification = findByUserIdAndTypeOptional(notification);
        if(userNotification.isPresent())
            delete(userNotification.get());
    }

    public List<UserNotification> getUsersByType(String notificationType) {
        return find("notificationType", notificationType)
        .list();
    }
}