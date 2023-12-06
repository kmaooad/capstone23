package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class AddNotification {

    @NotEmpty
    private String userId;

    @NotEmpty
    @Pattern(regexp = "ADDED_TO_CHAT|DELETED_FROM_CHAT")
    private String notificationType;

    @NotEmpty
    @Pattern(regexp = "EMAIL|SMS|MESSENGER")
    private String sendingMethod;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getSendingMethod() {
        return sendingMethod;
    }

    public void setSendingMethod(String sendingMethod) {
        this.sendingMethod = sendingMethod;
    }
}
