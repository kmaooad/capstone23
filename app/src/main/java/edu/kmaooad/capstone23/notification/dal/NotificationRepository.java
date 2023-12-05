package edu.kmaooad.capstone23.notification.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification){
        persist(notification);
        return notification;
    }
}