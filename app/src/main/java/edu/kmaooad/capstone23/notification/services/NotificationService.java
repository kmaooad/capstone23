package edu.kmaooad.capstone23.notification.services;

import edu.kmaooad.capstone23.notification.dal.Notification;
import org.bson.types.ObjectId;

public interface NotificationService {
    Notification insert(Notification notification);
    Notification findNotificationBy(ObjectId userId, String notificationType);
}