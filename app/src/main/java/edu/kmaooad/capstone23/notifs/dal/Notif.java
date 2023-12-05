package edu.kmaooad.capstone23.notifs.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notifications")
public class Notif {
    public ObjectId id;
    public ObjectId userId;
    public String notificationType;//type of notif
    public String notificationMethod;//method of notifying
}