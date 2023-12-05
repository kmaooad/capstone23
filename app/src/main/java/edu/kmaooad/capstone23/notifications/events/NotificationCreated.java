package edu.kmaooad.capstone23.notifications.events;

public class NotificationCreated {
    private final String id;

    public NotificationCreated(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
