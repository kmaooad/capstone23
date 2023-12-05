package edu.kmaooad.capstone23.notification.commands;

import edu.kmaooad.capstone23.notification.dal.NotificationDestination;

public class SetNotificationPreferenceCommand {
    public String eventType;

    public NotificationDestination destination;

    public String userId;
}
