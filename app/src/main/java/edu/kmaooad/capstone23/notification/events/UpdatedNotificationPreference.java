package edu.kmaooad.capstone23.notification.events;

import edu.kmaooad.capstone23.notification.dal.NotificationDestination;
import org.bson.types.ObjectId;

public class UpdatedNotificationPreference {
    public ObjectId id;

    public String eventType;

    public NotificationDestination destination;

    public ObjectId userId;
}
