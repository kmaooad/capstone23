package edu.kmaooad.capstone23.notification.services;
import edu.kmaooad.capstone23.notification.dal.Notification;

public interface  NotificationService {
    Notification insert(Notification notification);
    void sending(Notification notification);
}
