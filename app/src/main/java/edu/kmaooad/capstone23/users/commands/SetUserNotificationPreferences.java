package edu.kmaooad.capstone23.users.commands;

import edu.kmaooad.capstone23.notifications.models.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

public class SetUserNotificationPreferences {
    @NotBlank
    @Size(min = 2, max = 50)
    String userId;
    ArrayList<NotificationType> notificationTypes;


    public String getUserId() {
        return userId;
    }

    public ArrayList<NotificationType> getNotificationTypes() {
        return notificationTypes;
    }
}
