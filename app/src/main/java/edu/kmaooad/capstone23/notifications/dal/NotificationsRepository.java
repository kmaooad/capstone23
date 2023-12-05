package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

import org.bson.types.ObjectId;

@ApplicationScoped
public class NotificationsRepository implements PanacheMongoRepository<Notification> {

    public Notification findById(String id) {
        return findById(new ObjectId(id));
    }

    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }
    public void delete(Notification notification) {
        deleteById(notification.id);
    }

    public Optional<Notification> findByIdAndSource(String userId, NotificationSourceEvent source){
        return find("user = :userId and event = :source", Parameters.with("userId", userId).and("event", source)).firstResultOptional();

    }
}
