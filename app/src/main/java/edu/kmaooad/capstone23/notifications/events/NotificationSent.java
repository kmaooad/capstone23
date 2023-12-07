package edu.kmaooad.capstone23.notifications.events;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public class NotificationSent {
    private Notification notification;

	public NotificationSent(Notification notification){
        this.notification = notification;
    }

	public Notification getNotification() {
		return notification;
	}

}
