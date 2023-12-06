package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;

public interface NotificationService {
    Notification insert(Notification notification);

//    use this to send notification after action
    boolean notify(String userInfo, NotificationType type, String message);
}
