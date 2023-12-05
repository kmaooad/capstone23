package edu.kmaooad.capstone23.notifications.observers;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public class EmailNotificationObserver implements NotificationObserver{

    @Override
    public void notify(Notification notification) {
        System.out.print(" EMAIL: " + notification.getMessage());
    }

}
