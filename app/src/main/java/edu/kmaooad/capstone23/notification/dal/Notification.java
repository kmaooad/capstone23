package edu.kmaooad.capstone23.notification.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notification")
public class Notification {
    public ObjectId notificationId;
    public String notificationContent;//text notification
    public String notificationAbout;//type of notification
    public String sendingProgramToUse;//ел.поштою/месенджерами/смс
}
