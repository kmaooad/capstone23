package edu.kmaooad.capstone23.notifications.events;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public class NotificationCreated {
    private Notification notification;

	public NotificationCreated(Notification notification){
        this.notification = notification;
    }

	public Notification getNotification() {
		return notification;
	}

}
