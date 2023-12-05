package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.dal.NotificationSourceEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationChannel;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotEmpty;

public class CreateNotificationCommand {
    @NotEmpty
    private String userId;

    @NotNull
	private NotificationSourceEvent source;

    @NotNull
    private NotificationChannel channel;

	public CreateNotificationCommand(String userId, NotificationSourceEvent source, NotificationChannel channel){
        this.userId = userId;
        this.source = source;
        this.channel = channel;
    }

    public NotificationChannel getChannel() {
		return channel;
	}

	public void setChannel(NotificationChannel channel) {
		this.channel = channel;
	}


    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public NotificationSourceEvent getSource() {
		return source;
	}

	public void setSource(NotificationSourceEvent source) {
		this.source = source;
	}

}
