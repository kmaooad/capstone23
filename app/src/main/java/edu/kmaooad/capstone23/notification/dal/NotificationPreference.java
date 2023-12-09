package edu.kmaooad.capstone23.notification.dal;

import org.bson.types.ObjectId;

public class NotificationPreference {
    public ObjectId id;

    public String eventType;

    public NotificationDestination destination;

    public ObjectId userId;
}
