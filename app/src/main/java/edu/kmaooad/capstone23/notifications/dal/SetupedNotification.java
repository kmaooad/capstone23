package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "SetupedNotifications")
public class SetupedNotification {
    public ObjectId id;
    public ObjectId memberId;
    public NotificationPlaceToSend notificationPlaceToSend;
    public NotificationOnWhatEventProceed notificationOnWhatEventProceed;
}
