package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ScheduleNotification {

    @NotEmpty
    private String memberId;

    @NotNull
    @Pattern(regexp = "sms|telegram|email")
    private String notificationType;

    @NotNull
    @Pattern(regexp = "USER_BANNED|ADDED_TO_GROUP")
    private String notificationTriggerType;

    public String getNotificationTriggerType() {
        return notificationTriggerType;
    }

    public void setNotificationTriggerType(String notificationTriggerType) {
        this.notificationTriggerType = notificationTriggerType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}
