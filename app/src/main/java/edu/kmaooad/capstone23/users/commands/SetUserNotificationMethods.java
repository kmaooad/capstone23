package edu.kmaooad.capstone23.users.commands;

import edu.kmaooad.capstone23.notifications.models.NotificationMethod;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

public class SetUserNotificationMethods {
    @NotBlank
    private String userId;
    private ArrayList<NotificationMethod> notificationMethods;

    public String getUserId() {
        return userId;
    }

    public ArrayList<NotificationMethod> getNotificationMethods() {
        return notificationMethods;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNotificationMethods(ArrayList<NotificationMethod> notificationMethods) {
        this.notificationMethods = notificationMethods;
    }
}
