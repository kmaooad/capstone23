package edu.kmaooad.capstone23.notifs.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class NotifCommand {

    @NotEmpty
    private String userId;

    @NotEmpty
    @Pattern(regexp = "Subscribed_to_notifications|Unsubscribed_to_notifications")
    private String notificationStatus;
    @NotEmpty
    @Pattern(regexp = "email|sms|telegram")
    private String notificationMethod;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    //TODO: FINISH NOTIF COMMAND (SUBSCRIPTION TO SERVICE)
}
