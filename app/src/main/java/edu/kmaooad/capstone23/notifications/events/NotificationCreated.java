
package edu.kmaooad.capstone23.notifications.events;

public class NotificationCreated {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public NotificationCreated(String id) {
        this.id = id;
    }
}