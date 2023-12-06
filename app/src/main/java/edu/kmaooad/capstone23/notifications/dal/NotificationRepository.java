package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

public class NotificationRepository implements PanacheMongoRepository<Notification> {
    public Notification insert(Notification notification) {
        persist(notification);
        return notification;
    }
}
