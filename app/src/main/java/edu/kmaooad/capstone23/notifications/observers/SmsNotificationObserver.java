package edu.kmaooad.capstone23.notifications.observers;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public class SmsNotificationObserver implements NotificationObserver {

    @Override
    public void notify(Notification notification) {
        System.out.print(" SMS: " + notification.getMessage());
    }
}
