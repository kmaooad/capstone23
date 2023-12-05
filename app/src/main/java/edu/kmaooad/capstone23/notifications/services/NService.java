package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NType;

public interface NotificationService {
    Notification insert(Notification notification);

    boolean notify(String uInfo, NType type, String message);
}