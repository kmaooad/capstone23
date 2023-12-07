package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import io.smallrye.common.constraint.NotNull;

public class SendNotificationCommand {
    @NotNull
    private Notification notification;

    public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public SendNotificationCommand(Notification notification){
        this.notification = notification;
    }


}
