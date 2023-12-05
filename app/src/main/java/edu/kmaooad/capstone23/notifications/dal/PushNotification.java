package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "push-notifications")
public class PushNotification {
    public ObjectId id;
    public ObjectId userId;
    public String notificationEvent;
    public String notificationDestination;
}
