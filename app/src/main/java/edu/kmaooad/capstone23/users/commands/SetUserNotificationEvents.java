package edu.kmaooad.capstone23.users.commands;

import edu.kmaooad.capstone23.notifications.models.Event;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

public class SetUserNotificationEvents {
    @NotBlank
    private String userId;
    private ArrayList<Event> notificationEvents;

    public String getUserId() {
        return userId;
    }

    public void setNotificationEvents(ArrayList<Event> notificationEvents) {
        this.notificationEvents = notificationEvents;
    }

    public ArrayList<Event> getNotificationEvents() {
        return notificationEvents;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
