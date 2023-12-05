package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "notificationTypes")
public class NotificationType {
    public String id;
    public String userId;
    public String notificationType;
    public String notificationMethod;
}
