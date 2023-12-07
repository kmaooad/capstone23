package edu.kmaooad.capstone23.notifications.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "notifications")
public class Notification {
    public ObjectId id;
    public ObjectId user;
    public NotificationChannel channel;
    public NotificationSourceEvent event;
    public boolean sent;
}
