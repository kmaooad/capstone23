package edu.kmaooad.capstone23.notifications.util;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public interface NotificationChannelService {
    public void sendNotification(Notification notification);
}
