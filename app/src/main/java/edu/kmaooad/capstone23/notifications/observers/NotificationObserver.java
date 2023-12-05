package edu.kmaooad.capstone23.notifications.observers;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public interface NotificationObserver {

    void notify(Notification notification);

}
