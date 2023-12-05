package edu.kmaooad.capstone23.notifications.events;

public class NotificationSettingCreated {

    private String id;

    public NotificationSettingCreated(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
