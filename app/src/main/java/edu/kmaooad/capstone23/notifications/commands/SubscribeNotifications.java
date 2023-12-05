package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class SubscribeNotifications {
    @NotEmpty
    private String userId;

    @NotEmpty
    @Pattern(regexp = "Subscribed_on_notifications|Unsubscribed_on_notifications")
    private String notificationStatus;
    @NotEmpty
    @Pattern(regexp = "email|sms|telegram")
    private String subscriptionMethod;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String type) {
        this.notificationStatus = type;
    }

    public String getSubscriptionMethod() {
        return subscriptionMethod;
    }

    public void setSubscriptionMethod(String subscriptionMethod) {
        this.subscriptionMethod = subscriptionMethod;
    }
}
